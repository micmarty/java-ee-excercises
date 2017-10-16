package pl.gda.pg.eti.kask.javaee.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Author;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Book;

/**
 *
 * @author psysiu
 */

@ManagedBean
@ApplicationScoped
public class BookService implements Serializable {

    private final SortedMap<Integer, Book> books;
    
    private final SortedMap<Integer, Author> authors;

    public BookService() {
        Author a1 = new Author(1, "Dmitrij", "Glukhovsky");
        Author a2 = new Author(2, "Maja", "Kossakowska");
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 1, 24);
        Book b1 = new Book(1, "Metro 2033", cal.getTime(), asList(a1));
        cal.set(2011, 9, 22);
        Book b2 = new Book(2, "Grillbar Galaktyka", cal.getTime(), asList(a2));
        books = new TreeMap<>();
        books.put(b1.getId(), b1);
        books.put(b2.getId(), b2);
        authors = new TreeMap<>();
        authors.put(a1.getId(), a1);
        authors.put(a2.getId(), a2);
    }
    
    private List<Author> asList(Author... authors) {
        List<Author> list = new ArrayList<>();
        list.addAll(Arrays.asList(authors));
        return list;
    }

    public List<Book> findAllBooks() {
        return new ArrayList<>(books.values());
    }

    public Book findBook(int id) {
        return books.get(id);
    }

    public void removeBook(Book book) {
        books.remove(book.getId());
    }

    public void saveBook(Book book) {
        if (book.getId() == 0) {
            book.setId(books.lastKey() + 1);
        }
        books.put(book.getId(), book);
    }
    
    public List<Author> findAllAuthors() {
        return new ArrayList<>(authors.values());
    }
    
    public Author findAuthor(int id) {
        return authors.get(id);
    }
}
