package pl.gda.pg.eti.kask.javaee.enterprise.web.view.forests;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.*;
import pl.gda.pg.eti.kask.javaee.enterprise.forests.ForestService;
import pl.gda.pg.eti.kask.javaee.enterprise.web.view.auth.AuthContext;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ConversationScoped
public class EditElf implements Serializable {

    @EJB
    ForestService forestService;

    @Inject
    AuthContext authContext;

    @Inject
    Conversation conversation;

    @Getter
    @Setter
    private Elf elf;

    public BowType[] getBowTypes() {
        return BowType.values();
    }

    @Getter
    List<Forest> availableForests;



    @PostConstruct
    public void init() {
        availableForests = findAvailableForests();
        conversation.begin();
    }

    public List<Forest> findAvailableForests(){
        List<Forest> allForests =  forestService.findAllForests();
        List<Forest> allowedForests = new ArrayList<>();
        boolean isAdmin = authContext.isUserInRole(User.Roles.ADMIN);
        String login = authContext.getUserLogin();

        for (Forest f : allForests) {
            boolean isOwner = f.getOwner().getLogin().equals(login);
            if (isAdmin || isOwner) {
                allowedForests.add(f);
            }
        }
        return allowedForests;
    }

    public void preRenderView() {
        if (elf == null) {
            elf = new Elf();
            elf.setOwner(authContext.getCurrentUser());
        }
    }

    public String saveElf() {
        conversation.end();
        forestService.saveElf(elf);
        return "/forests/list_forests.xhtml?faces-redirect=true";
    }

    public String cancel() {
        conversation.end();
        return "/forests/list_forests.xhtml?faces-redirect=true";
    }

    public String next() {
        return "/forests/edit_elf_2.xhtml";
    }

    public String back() {
        return "/forests/edit_elf_1.xhtml";
    }
}
