package pl.gda.pg.eti.kask.javaee.enterprise.web.view.books;


import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.enterprise.books.SerwisLasu;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
@Log
public class DodajStrzaly implements Serializable {

    @EJB
    private SerwisLasu serwisLasu;



    public String dodajStrzaly(int a) {

        serwisLasu.dodajStrzaly(a);
        return "list_lasy?faces-redirect=true";
    }
}
