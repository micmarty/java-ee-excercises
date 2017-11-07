package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.ElfService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Elf;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Las;

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
import pl.gda.pg.eti.kask.javaee.jsf.entities.Elf.RodzajLuku;

@ViewScoped
@ManagedBean
@Log
public class EditElf implements Serializable {

    @ManagedProperty("#{elfService}")
    private ElfService elfService;

    @Getter
    @Setter
    private int elfId;
    
//    @Getter
//    @Setter
//    private int forestId;

    @Getter
    @Setter
    private Elf elf;
    
    private List<SelectItem> lasyAsSelectItems;
    private List<SelectItem> bowCategoriesAsSelectItems;

    public void setElfService(ElfService elfService) {
        this.elfService = elfService;
    }
    
    public List<SelectItem> getLasyAsSelectItems() {
        if (lasyAsSelectItems == null) {
            lasyAsSelectItems = new ArrayList<>();
            for (Las las : elfService.findAllLasy()) {
                lasyAsSelectItems.add(new SelectItem(las, Integer.toString(las.getId())));
            }
        }
        return lasyAsSelectItems;
    }
    
    public List<SelectItem> getBowCategoriesAsSelectItems() {
        if (bowCategoriesAsSelectItems == null) {
            bowCategoriesAsSelectItems = new ArrayList<>();
            for (RodzajLuku bowCategory : RodzajLuku.values()) {
                bowCategoriesAsSelectItems.add(new SelectItem(bowCategory, bowCategory.name()));
            }
        }
        return bowCategoriesAsSelectItems;
    }

    public void init() {
        if (elf == null && elfId != 0) {
            elf = elfService.findElf(elfId);
        } else if (elf == null && elfId == 0) {
            elf = new Elf();
        }
        if (elf == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    public String saveElf() {
        elfService.saveElf(elf);
        return "list_elves?faces-redirect=true";
    }
}
