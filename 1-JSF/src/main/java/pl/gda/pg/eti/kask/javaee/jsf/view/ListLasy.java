package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.KatalogService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pl.gda.pg.eti.kask.javaee.jsf.entities.Elf;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Las;

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
public class ListLasy implements Serializable {

    @ManagedProperty("#{katalogService}")
    private KatalogService katalogService;

    public void setKatalogService(KatalogService katalogService) {
        this.katalogService = katalogService;
    }
    private List<Las> lasy;

    public List<Las> getLasy() {
        if (lasy == null) {
            lasy = katalogService.findAllLasy();
        }
        return lasy;
    }

    public void removeLas(Las las) {
        katalogService.removeLas(las);
        lasy.remove(las);
    }

    public void removeElf(Elf elf) {
        katalogService.removeElf(elf);
    }
}
