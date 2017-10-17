package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.KatalogService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Elf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
public class ViewElf implements Serializable {

    @ManagedProperty("#{katalogService}")
    private KatalogService katalogService;

    @Getter
    @Setter
    private int elfId;

    @Getter
    @Setter
    private Elf elf;

    public void setKatalogService(KatalogService katalogService) {
        this.katalogService = katalogService;
    }
    
    public void init() {
        if (elf == null) {
            elf = katalogService.findElf(elfId);
        }
        if (elf == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

}
