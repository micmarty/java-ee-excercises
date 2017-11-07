package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.ElfService;
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
import pl.gda.pg.eti.kask.javaee.jsf.entities.Elf;

@ViewScoped
@ManagedBean
@Log
public class ViewElf implements Serializable {

    @ManagedProperty("#{elfService}")
    private ElfService elfService;

    @Getter
    @Setter
    private int elfId;

    @Getter
    @Setter
    private Elf elf;

    public void setElfService(ElfService elfService) {
        this.elfService = elfService;
    }
    
    public void init() {
        if (elf == null) {
            elf = elfService.findElf(elfId);
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
