package pl.gda.pg.eti.kask.javaee.enterprise;

import pl.gda.pg.eti.kask.javaee.enterprise.entities.Elf;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Las;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.RodzajeLuku;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.*;

//@ManagedBean
//@ApplicationScoped
//@Log
@Stateless
@LocalBean
public class SerwisLasu implements Serializable {


    @PersistenceContext
    EntityManager em;


    public SerwisLasu() {

    }


    public List<Las> dajMnieLasy() {
        return em.createNamedQuery(Las.FIND_ALL, Las.class).getResultList();
    }

    public Las dajMnieLas(int id) {
        return em.find(Las.class, id);
    }


    public void usunLas(Las las) {
        em.remove(em.merge(las));
    }

    public void usunElf(Elf book) {
        em.remove(em.merge(book));
    }

    public void zapiszLas(Las las) {
        if (las.getId() == null) {
            em.persist(las);
        } else {
            em.merge(las);
        }

    }

    public Elf dajMnieElfa(int elfId) {
        return em.find(Elf.class, elfId);
    }

    public void zapiszElfa(Elf elf) {
        if (elf.getId() == null) {
            em.persist(elf);
        } else {
            em.merge(elf);
        }

    }

    public RodzajeLuku dajMnieRodzajLuku(String value) {
        for (RodzajeLuku rl : RodzajeLuku.values()) {
            if (rl.toString().equals(value))
                return rl;
        }
        return null;
    }

    public void dodajStrzaly(int value) {
        Query q = em.createNamedQuery(Elf.dodajStrzal);
        q.setParameter("dodatkowe_strzaly", value);
        q.executeUpdate();
    }
}
