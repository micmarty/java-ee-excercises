package pl.gda.pg.eti.kask.javaee.enterprise.web.view.forests;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.enterprise.forests.ForestService;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.*;
import pl.gda.pg.eti.kask.javaee.enterprise.web.view.auth.AuthContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class EditForest implements Serializable {

    @EJB
    ForestService forestService;

    @Inject
    AuthContext authContext;

    @Inject
    Conversation conversation;

    @Getter
    @Setter
    private Forest forest;

    @Getter
    List<Author> availableAuthors;

    @PostConstruct
    public void init() {
        //availableAuthors = forestService.findAllElves();
        conversation.begin();
    }

    public void preRenderView() {
        if (forest == null) {
            forest = new Forest();
            forest.setOwner(authContext.getCurrentUser());
        }
    }

    public String saveForest() {
        conversation.end();
        forestService.saveForest(forest);
        return "/forests/list_forests.xhtml?faces-redirect=true";
    }

    public String cancel() {
        conversation.end();
        return "/forests/list_forests.xhtml?faces-redirect=true";
    }

//    public String next() {
//        return "/books/edit_book_2.xhtml";
//    }
//
//    public String back() {
//        return "/books/edit_book_1.xhtml";
//    }
}
