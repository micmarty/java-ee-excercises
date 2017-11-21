package pl.gda.pg.eti.kask.javaee.enterprise.web.view.books;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.enterprise.books.SerwisLasu;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Elf;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Las;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.RodzajeLuku;
import pl.gda.pg.eti.kask.javaee.enterprise.users.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
public class EditElf implements Serializable {

    @EJB
    private SerwisLasu serwisLasu;

    @EJB
    private UserService userService;

    @Getter
    @Setter
    private int elfId;

    @Getter
    @Setter
    private Elf elf;

    @Setter
    private List<SelectItem> lasAsSelectItems;

    @Setter
    private List<SelectItem> rodzajLukuAsSelectItems;

    public void setSerwisLasu(SerwisLasu serwisLasu) {
        this.serwisLasu = serwisLasu;
    }

    public List<SelectItem> getLasAsSelectItems() {
        if (lasAsSelectItems == null) {
            lasAsSelectItems = new ArrayList<>();
            for (Las las : serwisLasu.dajMnieLasy()) {
                lasAsSelectItems.add(new SelectItem(las, las.getId() + "" ));
            }
        }
        return lasAsSelectItems;
    }

    public List<SelectItem> getRodzajLukuAsSelectItemsAsSelectItems() {
        if (rodzajLukuAsSelectItems == null) {
            rodzajLukuAsSelectItems = new ArrayList<>();
            for (RodzajeLuku rodzaj : RodzajeLuku.values()) {
                rodzajLukuAsSelectItems.add(new SelectItem(rodzaj, rodzaj.toString() ));
            }
        }
        return rodzajLukuAsSelectItems;
    }

    public void init() {
        if (elf == null && elfId != 0) {
            elf = serwisLasu.dajMnieElfa(elfId);
        } else if (elf == null && elfId == 0) {
            elf = new Elf();
            elf.setOwner(userService.findCurrentUser());

        }/**/
        if (elf == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    public String saveElf() {
        serwisLasu.zapiszElfa(elf);
        return "list_lasy?faces-redirect=true";
    }
}
