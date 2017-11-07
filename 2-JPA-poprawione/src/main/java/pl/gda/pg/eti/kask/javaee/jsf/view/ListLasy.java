package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.ElfService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Elf;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Las;

@ViewScoped
@ManagedBean
public class ListLasy implements Serializable {

    @ManagedProperty("#{elfService}")
    private ElfService elfService;

    public void setElfService(ElfService elfService) {
        this.elfService = elfService;
    }
    private List<Las> lasy;
    
    public List<Las> getLasy() {
        if (lasy == null) {
            lasy = elfService.findAllLasy();
        }
        return lasy;
    }

    public String removeElf(Elf elf) {
        elfService.removeElf(elf);
        return "list_elves?faces-redirect=true";
    }
    
    public String removeLas(Las las) {
        elfService.removeLas(las);
        lasy.remove(las);
        return "list_elves?faces-redirect=true";
    }
}
