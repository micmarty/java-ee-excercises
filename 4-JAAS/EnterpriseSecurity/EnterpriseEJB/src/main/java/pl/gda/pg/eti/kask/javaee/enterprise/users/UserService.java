/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gda.pg.eti.kask.javaee.enterprise.users;

import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.CryptUtils;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.User;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author psysiu
 */
@Stateless
@LocalBean
@Log
@DeclareRoles(value = {"Admin", "User"})
public class UserService {

    @PersistenceContext
    EntityManager em;

    @Resource
    SessionContext sessionCtx;

    @RolesAllowed(User.Roles.ADMIN)
    public List<User> findAllUsers() {
        return em.createNamedQuery(User.Queries.FIND_ALL, User.class).getResultList();
    }

    @RolesAllowed(User.Roles.ADMIN)
    public User findUser(String login) {
        return findUserByLogin(login);
    }

    @RolesAllowed({User.Roles.ADMIN, User.Roles.USER})
    public User findCurrentUser() {
        String login = sessionCtx.getCallerPrincipal().getName();
        return findUserByLogin(login);
    }

    private User findUserByLogin(String login) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    public void changePassword(Integer userId, String password) {
        boolean isAdmin = sessionCtx.isCallerInRole(User.Roles.ADMIN);
        boolean isOwner = sessionCtx.getCallerPrincipal().getName().equals(findUserById(userId).getLogin());
        if (isAdmin || isOwner) {
            Query query= em.createNativeQuery("UPDATE User e SET e.password = :pass WHERE e.id = :id ");
            query.setParameter("pass", CryptUtils.sha256(password));
            query.setParameter("id", userId);
            query.executeUpdate();
        }
    }

//    private int findUserIdByName(String login){
//        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.login = :name", User.class);
//        query.setParameter("login", name);
//        return query.getSingleResult();
//    }

    private User findUserById(Integer id) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
