package pl.gda.pg.eti.kask.javaee.enterprise.web.view.books;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Book;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ViewScoped
@ManagedBean
public class ViewBook implements Serializable {

    @Getter
    @Setter
    private Book book;
}
