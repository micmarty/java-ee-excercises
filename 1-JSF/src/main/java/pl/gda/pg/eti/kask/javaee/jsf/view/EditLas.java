package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.KatalogService;
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

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
@Log
public class EditLas implements Serializable {

    @ManagedProperty("#{katalogService}")
    private KatalogService katalogService;

    @Getter
    @Setter
    private int lasId;

    @Getter
    @Setter
    private Las las;
    
    private List<SelectItem> authorsAsSelectItems;

    public void setkatalogService(KatalogService katalogService) {
        this.katalogService = katalogService;
    }
    
    public List<SelectItem> getAuthorsAsSelectItems() {
        if (authorsAsSelectItems == null) {
            authorsAsSelectItems = new ArrayList<>();
            for (Elf author : katalogService.findAllElfy()) {
                authorsAsSelectItems.add(new SelectItem(author, author.getImie() + " " + author.getLiczbaStrzal()));
            }
        }
        return authorsAsSelectItems;
    }

    public void init() {
        if (las == null && lasId != 0) {
            las = katalogService.findLas(lasId);
        } else if (las == null && lasId == 0) {
            las = new Las();
            las.setkatalogService(katalogService);
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
        katalogService.saveLas(las);
        return "list_books?faces-redirect=true";
    }
}
