package pl.gda.pg.eti.kask.javaee.enterprise.web.view;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.enterprise.users.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.logging.Level;

@ManagedBean
@ViewScoped
@Log
public class EditPassword {

    @EJB
    UserService userService;

    @Getter
    @Setter
    Integer userId;

    @Getter
    @Setter
    String password;

    @Getter
    @Setter
    String userLogin;

    public void saveChanges() {
        userService.changePassword(userId, password);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
        } catch (IOException e) {
            log.log(Level.SEVERE, null, e);
        }
    }

}
