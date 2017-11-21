package pl.gda.pg.eti.kask.javaee.enterprise.web.view.books;

import pl.gda.pg.eti.kask.javaee.enterprise.books.BookService;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Author;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
public class ListAuthors implements Serializable {

    @EJB
    BookService bookService;

    private List<Author> authors;

    public List<Author> getAuthors() {
        if (authors == null) {
            authors = bookService.findAllAuthors();
        }
        return authors;
    }
}
