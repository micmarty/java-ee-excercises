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
public class EditElf implements Serializable { 
 
    @ManagedProperty("#{katalogService}") 
    private KatalogService katalogService; 
 
    @Getter 
    @Setter 
    private int elfId; 
 
    @Getter 
    @Setter 
    private Elf elf; 
     
    private List<SelectItem> lasyDoZamieszkaniaAsSelectItems; 

    public void setkatalogService(KatalogService katalogService) { 
        this.katalogService = katalogService; 
    } 
     
    public List<SelectItem> getLasyDoZamieszkaniaAsSelectItems() { 
        if (lasyDoZamieszkaniaAsSelectItems == null) { 
            lasyDoZamieszkaniaAsSelectItems = new ArrayList<>(); 
            for (Las las : katalogService.findAllLasy()) { 
                lasyDoZamieszkaniaAsSelectItems.add(new SelectItem(las, String.valueOf(las.getLiczbaDrzew()))); 
            } 
        } 
        return lasyDoZamieszkaniaAsSelectItems; 
    } 
 
    // Edycja/tworzenie elfa 
    public void init() { 
        if (elf == null && elfId != 0) { 
            elf = katalogService.findElf(elfId); 
        } else if (elf == null && elfId == 0) { 
            elf = new Elf(); 
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
        katalogService.saveElf(elf); 
        return "list_books?faces-redirect=true"; 
    }
}