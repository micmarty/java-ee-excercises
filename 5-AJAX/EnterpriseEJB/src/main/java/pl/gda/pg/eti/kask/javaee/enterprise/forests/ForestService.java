package pl.gda.pg.eti.kask.javaee.enterprise.forests;

import pl.gda.pg.eti.kask.javaee.enterprise.forests.auth.OwnerOrAdminAllowed;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Forest;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.User;
import pl.gda.pg.eti.kask.javaee.enterprise.events.ForestEvent;
import pl.gda.pg.eti.kask.javaee.enterprise.events.qualifiers.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Stateless
public class ForestService implements Serializable {

    @PersistenceContext
    EntityManager em;

    @Inject
    Event<ForestEvent> forestEvent;


    @PermitAll
    public List<Forest> findAllForests() {
        return em.createNamedQuery(Forest.Queries.FIND_ALL, Forest.class).getResultList();
    }

    @PermitAll
    public List<Forest> findRecentForests(int limit) {
        TypedQuery<Forest> query = em.createNamedQuery(Forest.Queries.FIND_MOST_RECENT, Forest.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @PermitAll
    public Forest findForest(int id) {
        return em.find(Forest.class, id);
    }

    @RolesAllowed(User.Roles.ADMIN)
    public void removeForest(Forest forest) {
        forest = em.merge(forest);
        em.remove(forest);
        forestEvent.select(ForestDeletion.Literal).fire(ForestEvent.of(forest));
    }

    //@PermitAll
    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    @OwnerOrAdminAllowed
    public void saveForest(Forest forest) {
        if (forest.getId() == null) {
            em.persist(forest);
            forestEvent.select(ForestCreation.Literal).fire(ForestEvent.of(forest));
        } else {
            em.merge(forest);
            forestEvent.select(ForestModification.Literal).fire(ForestEvent.of(forest));
        }
    }

//    @PermitAll
//    public List<Author> findAllAuthors() {
//        return em.createNamedQuery(Author.Queries.FIND_ALL, Author.class).getResultList();
//    }
//
//    @PermitAll
//    public Author findAuthor(int id) {
//        return em.find(Author.class, id);
//    }

//    @PermitAll
//    public Map<Author, Long> countForestsPerAuthor() {
//        TypedQuery<Object[]> query = em.createNamedQuery(Author.Queries.COUNT_BOOKS_PER_AUTHOR, Object[].class);
//        List<Object[]> results = query.getResultList();
//        Map<Author, Long> forestsCountPerAuthor = results.stream()
//                .collect(toMap(result -> (Author) result[0], result -> (Long) result[1]));
//        return forestsCountPerAuthor;
//    }

//    @RolesAllowed(User.Roles.ADMIN)
//    public void saveAuthor(Author author) {
//        if (author.getId() == null) {
//            em.persist(author);
//        } else {
//            em.merge(author);
//        }
//    }
}
