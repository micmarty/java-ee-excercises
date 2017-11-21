package pl.gda.pg.eti.kask.javaee.enterprise.web.view.auth;

import pl.gda.pg.eti.kask.javaee.enterprise.entities.User;
import pl.gda.pg.eti.kask.javaee.enterprise.users.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@ManagedBean
public class ListUsers implements Serializable {

    @EJB
    UserService userService;

    List<User> users;

    public List<User> getUsers() {
        if (users == null) {
            users = userService.findAllUsers();
        }
        
        return users;
    }
}
