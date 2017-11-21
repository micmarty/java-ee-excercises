package pl.gda.pg.eti.kask.javaee.enterprise.web.view.books;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.enterprise.books.SerwisLasu;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Las;
import pl.gda.pg.eti.kask.javaee.enterprise.users.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
@Log
public class EditLas implements Serializable {

    @EJB
    private SerwisLasu serwisLasu;

    @EJB
    private UserService userService;

    @Getter
    @Setter
    private int lasId;

    @Getter
    @Setter
    private Las las;

    public void setSerwisLasu(SerwisLasu serwisLasu) {
        this.serwisLasu = serwisLasu;
    }


    public void init() {
        if (las == null && lasId != 0) {
            las = serwisLasu.dajMnieLas(lasId);
        } else if (las == null && lasId == 0) {
            las = new Las();
            las.setOwner(userService.findCurrentUser());
        }/**/
        if (las == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    public String saveLas() {
        serwisLasu.zapiszLas(las);
        return "list_lasy?faces-redirect=true";
    }
}