package pl.gda.pg.eti.kask.javaee.enterprise.books;

import pl.gda.pg.eti.kask.javaee.enterprise.entities.Elf;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Las;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.RodzajeLuku;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.User;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@ManagedBean
//@ApplicationScoped
//@Log
@Stateless
@LocalBean
public class SerwisLasu implements Serializable{


    @PersistenceContext
    EntityManager em;

//    @Resource
//    UserTransaction utx;
    @Resource
    SessionContext sessionCtx;



    public SerwisLasu() {

//        Las l1 = new Las(1,15,new ArrayList<>());
//        Las l2 = new Las(2,13,new ArrayList<>());
//
//        Elf e1 = new Elf(1, "miczi", 12, RodzajeLuku.DREWNIANY,l1);
//        Elf e2 = new Elf(2, "aaa", 14, RodzajeLuku.METALOWY, l1);
//        Elf e3 = new Elf(3, "bbb", 16, RodzajeLuku.DREWNIANY, l1);
//        Elf e4 = new Elf(4, "ccc", 18, RodzajeLuku.DREWNIANY, l2);
//        Elf e5 = new Elf(5, "ddd", 19, RodzajeLuku.DREWNIANY, l2);
//
//        l1.setElfyLasu(asList(e1, e2, e3));
//        l2.setElfyLasu(asList(e4, e5));
//
//
//
//        lasy = new TreeMap<>();
//        lasy.put(l1.getId(), l1);
//        lasy.put(l2.getId(), l2);


    }

    @PermitAll
    public List<Las> dajMnieLasy(){
        List<Las> listaLasy =  em.createNamedQuery(Las.FIND_ALL, Las.class).getResultList();
        List<Las> front = new ArrayList<>();
        boolean isAdmin = sessionCtx.isCallerInRole(User.Roles.ADMIN);
        String login = sessionCtx.getCallerPrincipal().getName();


        for (Las l : listaLasy) {
            boolean isOwner = l.getOwner().getLogin().equals(login);
            if (isAdmin || isOwner) {
                front.add(l);

            }
        }

        return front;

    }

    @PermitAll
    public Las dajMnieLas(int id){
        return em.find(Las.class, id);
    }

    @RolesAllowed(User.Roles.ADMIN)
    public void usunLas(Las las) {
         em.remove(em.merge(las));
    }

    @RolesAllowed(User.Roles.ADMIN)
    public void usunElf(Elf book) {
        em.remove(em.merge(book));
    }

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public void zapiszLas(Las las) {
        if (las.getId() == null) {
            em.persist(las);
        } else {
            boolean isAdmin = sessionCtx.isCallerInRole(User.Roles.ADMIN);
            String login = sessionCtx.getCallerPrincipal().getName();
            boolean isOwner = las.getOwner().getLogin().equals(login);

            if (isAdmin || isOwner) {
                em.merge(las);
            } else {
                throw new SecurityException("Insufficient permissions to edit the book");
            }
        }

    }

    @PermitAll
    public Elf dajMnieElfa(int elfId) {
        return em.find(Elf.class, elfId);
    }

    @PermitAll
    public void zapiszElfa(Elf elf) {
        if (elf.getId() == null) {
            em.persist(elf);
        } else {
            boolean isAdmin = sessionCtx.isCallerInRole(User.Roles.ADMIN);
            String login = sessionCtx.getCallerPrincipal().getName();
            boolean isOwner = elf.getOwner().getLogin().equals(login);

            if (isAdmin || isOwner) {
                em.merge(elf);
            } else {
                throw new SecurityException("Insufficient permissions to edit the book");
            }
        }
    }
    @PermitAll
    public RodzajeLuku dajMnieRodzajLuku(String value) {
        for (RodzajeLuku rl : RodzajeLuku.values()) {
            if(rl.toString().equals(value))
                return rl;
        }
        return null;
    }
    @PermitAll
    public void dodajStrzaly(int a) {
        Query q = em.createNamedQuery(Elf.dodajStrzal);
        q.setParameter("dodatkowe_strzaly", a);
        q.executeUpdate();
    }
}
