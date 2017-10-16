package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.BookService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Book;

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
public class ListBooks implements Serializable {

    @ManagedProperty("#{bookService}")
    private BookService bookService;

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    private List<Book> books;

    public List<Book> getBooks() {
        if (books == null) {
            books = bookService.findAllBooks();
        }
        return books;
    }

    public void removeBook(Book book) {
        bookService.removeBook(book);
        books.remove(book);
    }
}
