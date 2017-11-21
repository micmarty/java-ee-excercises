package pl.gda.pg.eti.kask.javaee.enterprise.web.view.books;

import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.enterprise.books.BookService;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Book;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.User;
import pl.gda.pg.eti.kask.javaee.enterprise.web.view.auth.AuthContext;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@ManagedBean
public class ListBooks implements Serializable {

    @EJB
    BookService bookService;

    @ManagedProperty("#{authContext}")
    @Setter
    AuthContext authContext;

    private List<Book> books;

    public List<Book> getBooks() {
        if (books == null) {
            books = bookService.findAllBooks();
        }
        return books;
    }

    public String removeBook(Book book) {
        bookService.removeBook(book);
        books = null;
        return "list_books?faces-redirect=true";
    }

    public boolean canEdit(Book book) {
        return authContext.isUserInRole(User.Roles.ADMIN) ||
                book.getOwner().equals(authContext.getCurrentUser());
    }

}
