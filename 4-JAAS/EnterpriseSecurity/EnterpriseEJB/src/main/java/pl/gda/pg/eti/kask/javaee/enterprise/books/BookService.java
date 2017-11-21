package pl.gda.pg.eti.kask.javaee.enterprise.books;

import pl.gda.pg.eti.kask.javaee.enterprise.entities.Author;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Book;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.User;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class BookService implements Serializable {

    //...
    @PersistenceContext
    EntityManager em;

    @Resource
    SessionContext sessionCtx;

    @PermitAll
    public List<Book> findAllBooks() {
        return em.createNamedQuery(Book.Queries.FIND_ALL, Book.class).getResultList();
    }

    @PermitAll
    public Book findBook(int id) {
        return em.find(Book.class, id);
    }

    @RolesAllowed(User.Roles.ADMIN)
    public void removeBook(Book book) {
        em.remove(em.merge(book));
    }

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public void saveBook(Book book) {
        if (book.getId() == null) {
            em.persist(book);
        } else {
            boolean isAdmin = sessionCtx.isCallerInRole(User.Roles.ADMIN);
            String login = sessionCtx.getCallerPrincipal().getName();
            boolean isOwner = book.getOwner().getLogin().equals(login);

            if (isAdmin || isOwner) {
                em.merge(book);
            } else {
                throw new SecurityException("Insufficient permissions to edit the book");
            }
        }
    }

    @PermitAll
    public List<Author> findAllAuthors() {
        return em.createNamedQuery(Author.Queries.FIND_ALL, Author.class).getResultList();
    }

    @PermitAll
    public Author findAuthor(int id) {
        return em.find(Author.class, id);
    }
}
