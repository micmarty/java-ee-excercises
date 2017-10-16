package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Elf;
import pl.gda.pg.eti.kask.javaee.jsf.KatalogService;

/**
 *
 * @author psysiu
 */
@ManagedBean
@RequestScoped
public class AuthorConverter implements Converter {

    @ManagedProperty("#{katalogService}")
    private KatalogService katalogService;

    public void setKatalogService(KatalogService katalogService) {
        this.katalogService = katalogService;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ("---".equals(value)) {
            return null;
        }
        return katalogService.findElf(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "---";
        }
        return ((Elf) value).getId() + "";
    }
}
