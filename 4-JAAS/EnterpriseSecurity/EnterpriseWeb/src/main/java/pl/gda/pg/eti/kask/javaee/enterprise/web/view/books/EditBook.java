package pl.gda.pg.eti.kask.javaee.enterprise.web.view.books;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.enterprise.books.BookService;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Author;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Book;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Comics;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Cover;
import pl.gda.pg.eti.kask.javaee.enterprise.users.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@ManagedBean
public class EditBook implements Serializable {

    @EJB
    BookService bookService;

    @EJB
    UserService userService;

    @Getter
    @Setter
    private Book book;

    @Setter
    private boolean comics;

    List<Author> availableAuthors;

    public List<Author> getAvailableAuthors() {
        if (availableAuthors == null) {
            availableAuthors = bookService.findAllAuthors();
        }

        return availableAuthors;
    }

    public Cover[] getCoverTypes() {
        return Cover.values();
    }

    public void init() {
        if (book == null) {
            book = comics ? new Comics() : new Book();
            book.setOwner(userService.findCurrentUser());
        }
    }

    public String saveBook() {
        bookService.saveBook(book);
        return "list_books?faces-redirect=true";
    }

    public boolean isComics() {
        return book instanceof Comics;
    }
}
