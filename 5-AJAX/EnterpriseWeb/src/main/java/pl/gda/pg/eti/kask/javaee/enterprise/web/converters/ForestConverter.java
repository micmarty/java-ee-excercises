package pl.gda.pg.eti.kask.javaee.enterprise.web.converters;

import pl.gda.pg.eti.kask.javaee.enterprise.forests.ForestService;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Forest;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@RequestScoped
public class ForestConverter implements Converter {

    @EJB
    ForestService forestService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }

        Forest forest = forestService.findForest(Integer.parseInt(value));

        if (forest == null) {
            context.getExternalContext().setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
            context.responseComplete();
        }

        return forest;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Forest forest = (Forest) value;
        return forest.getId() != null ? Integer.toString(forest.getId()) : null;
    }
}
