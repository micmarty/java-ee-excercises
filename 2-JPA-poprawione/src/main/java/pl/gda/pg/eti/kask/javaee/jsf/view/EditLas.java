package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.ElfService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Las;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;

@ViewScoped
@ManagedBean(name = "editLas")
@Log
public class EditLas implements Serializable {

    @ManagedProperty("#{elfService}")
    private ElfService elfService;

    @Getter
    @Setter
    private int lasId;

    @Getter
    @Setter
    private Las las;


    public void setElfService(ElfService elfService) {
        this.elfService = elfService;
    }

    public void init() {
        if (las == null && lasId != 0) {
            las = elfService.findLas(lasId);
        } else if (las == null && lasId == 0) {
            las = new Las();
        }
        if (las == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    public String saveLas() {
        elfService.saveLas(las);
        return "list_elves?faces-redirect=true";
    }
}
