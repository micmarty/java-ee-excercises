package pl.gda.pg.eti.kask.javaee.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Elf;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Las;

/**
 *
 * @author psysiu
 */

@ManagedBean
@ApplicationScoped
public class KatalogService implements Serializable {

    private final SortedMap<Integer, Las> lasy;
    
    private final SortedMap<Integer, Elf> elfy;

    public KatalogService() {
        Las dzikusy = new Las(1, 4, this);
        Elf elf1 = new Elf(1, "Shira", 10, Elf.RodzajLuku.DREWNIANY,dzikusy.getId() );
        Elf elf2 = new Elf(2, "Menei", 20, Elf.RodzajLuku.DREWNIANY, dzikusy.getId());

        Las cywilizowani = new Las(2, 44, this);
        Elf elf3 = new Elf(3, "Artem", 44,Elf.RodzajLuku.JESIONOWY, cywilizowani.getId());
        Elf elf4 = new Elf(4, "Ahus", 999, Elf.RodzajLuku.JESIONOWY,cywilizowani.getId());

        lasy = new TreeMap<>();
        lasy.put(dzikusy.getId(), dzikusy);
        lasy.put(cywilizowani.getId(), cywilizowani);

        elfy = new TreeMap<>();
        elfy.put(elf1.getId(), elf1);
        elfy.put(elf2.getId(), elf2);
        elfy.put(elf3.getId(), elf3);
        elfy.put(elf4.getId(), elf4);
    }
    
    private List<Elf> asList(Elf... elfy) {
        List<Elf> list = new ArrayList<>();
        list.addAll(Arrays.asList(elfy));
        return list;
    }

    public List<Las> findAllLasy() {
        return new ArrayList<>(lasy.values());
    }

    public Las findLas(int id) {
        return lasy.get(id);
    }

    public void removeLas(Las las) {
        lasy.remove(las.getId());
    }

    public void saveElf(Elf elf) { 
        if (elf.getId() == 0) { 
            elf.setId(elfy.lastKey() + 1); 
        } 
        elfy.put(elf.getId(), elf); 
    }

    public void saveLas(Las las) {
        if (las.getId() == 0) {
            las.setId(lasy.lastKey() + 1);
        }
        lasy.put(las.getId(), las);
    }
    
    public List<Elf> findAllElfy() {
        return new ArrayList<>(elfy.values());
    }
    public List<Elf> findAllElfy(int id) {
        List<Elf> result = new ArrayList<>();

        for (Elf elf: elfy.values()) {
            if (elf.lasId == id) {
                result.add(elf);
            }
        }
        return result;
    }
    public Elf findElf(int id) {
        return elfy.get(id);
    }
    public void removeElf(Elf elf){
        elfy.remove(elf.getId());
    }
}
