package pl.gda.pg.eti.kask.javaee.jsf;

import java.io.Serializable;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Elf;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Las;


@ManagedBean
@ViewScoped
@Log
public class ElfService implements Serializable {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    public ElfService() {
    }
    
    public List<Elf> findAllElfy() {
        return em.createNamedQuery("Elf.findAll").getResultList();
    }

    public Elf findElf(int id) {
        return em.find(Elf.class, id);
    }
    
    public void removeElf(Elf elf) {
        try {
            utx.begin();
            elf = em.merge(elf);
            em.remove(elf);
            utx.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            try {
                utx.rollback();
            } catch (Exception ex1) {
                log.log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void saveElf(Elf elf) {
        try {
            utx.begin();
            if (elf.getId() == null) {
                em.persist(elf);
            } else {
                em.merge(elf);
            }
            utx.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            try {
                utx.rollback();
            } catch (Exception ex1) {
                log.log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public List<Las> findAllLasy() {
        return em.createNamedQuery("Las.findAll").getResultList();
    }

    public Las findLas(int id) {
        return em.find(Las.class, id);
    }
    
    public void removeLas(Las las) {
        try {
            utx.begin();
            las = em.merge(las);
            em.remove(las);
            utx.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            try {
                utx.rollback();
            } catch (Exception ex1) {
                log.log(Level.SEVERE, null, ex1);
            }
        }
    }    
    
    public void saveLas(Las las) {
        try {
            utx.begin();
            if (las.getId() == null) {
                em.persist(las);
            } else {
                em.merge(las);
            }
            utx.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            try {
                utx.rollback();
            } catch (Exception ex1) {
                log.log(Level.SEVERE, null, ex1);
            }
        }
    }
    
//    public void saveLas(Las las) {
//        if (las.getId() == 0) {
//            if(lass.isEmpty()) {
//                las.setId(1);
//            } else {
//                las.setId(lass.lastKey() + 1);
//            }
//        }
//        lass.put(las.getId(), las);
//    }
    
//    public void removeLas(Las las) {
//        lass.remove(las.getId());
//    }
}
