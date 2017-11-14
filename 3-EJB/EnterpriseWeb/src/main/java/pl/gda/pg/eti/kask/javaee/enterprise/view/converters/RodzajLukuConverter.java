package pl.gda.pg.eti.kask.javaee.enterprise.view.converters;
import pl.gda.pg.eti.kask.javaee.enterprise.SerwisLasu;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Las;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Elf;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.RodzajeLuku;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
@RequestScoped
public class RodzajLukuConverter implements Converter {

    @EJB
    private SerwisLasu serwisLasu;

    public void setSerwisLasu(SerwisLasu serwisLasu) {
        this.serwisLasu = serwisLasu;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ("---".equals(value)) {
            return null;
        }
        return serwisLasu.dajMnieRodzajLuku(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "---";
        }
        return ((RodzajeLuku) value).toString();
    }
}
