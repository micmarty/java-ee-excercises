package pl.gda.pg.eti.kask.javaee.enterprise.web.view.forests;

import lombok.Getter;
import pl.gda.pg.eti.kask.javaee.enterprise.books.BookService;
import pl.gda.pg.eti.kask.javaee.enterprise.books.ForestService;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Book;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Forest;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.User;
import pl.gda.pg.eti.kask.javaee.enterprise.web.view.auth.AuthContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ListForests implements Serializable {

    @EJB
    ForestService forestService;

    @Inject
    AuthContext authContext;

    @Getter
    private List<Forest> forests;

    @PostConstruct
    public void init() {
        forests = forestService.findAllForests();
    }

    public void removeForest(Forest forest) {
        forestService.removeForest(forest);
        init();
    }

    public boolean canEdit(Forest forest) {
        return authContext.isUserInRole(User.Roles.ADMIN) ||
                forest.getOwner().equals(authContext.getCurrentUser());
    }

    public boolean canRemove() {
        return authContext.isUserInRole(User.Roles.ADMIN);
    }
}
