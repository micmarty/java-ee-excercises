package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.KatalogService;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Las;

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
@Log
public class ViewLas implements Serializable {

    @ManagedProperty("#{katalogService}")
    private KatalogService katalogService;

    @Getter
    @Setter
    private int lasId;

    @Getter
    @Setter
    private Las las;

    public void setKatalogService(KatalogService katalogService) {
        this.katalogService = katalogService;
    }
    
    public void init() {
        if (las == null) {
            las = katalogService.findLas(lasId);
        }
        if (las == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

}
