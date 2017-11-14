package pl.gda.pg.eti.kask.javaee.enterprise.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.enterprise.SerwisLasu;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Las;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Elf;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.RodzajeLuku;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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