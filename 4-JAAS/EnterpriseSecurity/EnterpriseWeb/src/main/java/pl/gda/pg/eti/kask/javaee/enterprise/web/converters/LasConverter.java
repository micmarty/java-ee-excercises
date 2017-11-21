package pl.gda.pg.eti.kask.javaee.enterprise.web.converters;

import pl.gda.pg.eti.kask.javaee.enterprise.books.SerwisLasu;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Las;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
@RequestScoped
public class LasConverter implements Converter {

    @EJB
    private pl.gda.pg.eti.kask.javaee.enterprise.books.SerwisLasu serwisLasu;

    public void setSerwisLasu(pl.gda.pg.eti.kask.javaee.enterprise.books.SerwisLasu serwisLasu) {
        this.serwisLasu = serwisLasu;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ("---".equals(value)) {
            return null;
        }
        return serwisLasu.dajMnieLas(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "---";
        }
        return ((Las) value).getId() + "";
    }
}
