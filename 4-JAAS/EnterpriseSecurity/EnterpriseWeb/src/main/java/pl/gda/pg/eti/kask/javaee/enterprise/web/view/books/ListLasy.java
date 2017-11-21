package pl.gda.pg.eti.kask.javaee.enterprise.web.view.books;

import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.enterprise.books.SerwisLasu;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Elf;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Las;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.User;
import pl.gda.pg.eti.kask.javaee.enterprise.web.view.auth.AuthContext;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@ManagedBean
public class ListLasy implements Serializable {

    @EJB
    private SerwisLasu serwisLasu;

    @ManagedProperty("#{authContext}")
    @Setter
    AuthContext authContext;

    public void setSerwisLasu(SerwisLasu serwisLasu) {
        this.serwisLasu = serwisLasu;
    }
    private List<Las> lasy;

    public List<Las> getLasy() {
        if (lasy == null) {
            lasy = serwisLasu.dajMnieLasy();
        }
        return lasy;
    }

    public void usunLas(Las book) {
        serwisLasu.usunLas(book);
        lasy.remove(book);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void usunElf(Elf book) {
        serwisLasu.usunElf(book);
        // lasy.remove(book);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean canEdit(Las las) {
        return authContext.isUserInRole(User.Roles.ADMIN) ||
                las.getOwner().equals(authContext.getCurrentUser());
    }


    public String ownerName(Las las){
        return las.getOwner().getLogin();
    }
}
