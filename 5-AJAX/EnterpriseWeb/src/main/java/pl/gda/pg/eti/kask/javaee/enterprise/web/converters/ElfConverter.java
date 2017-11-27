package pl.gda.pg.eti.kask.javaee.enterprise.web.converters;

import pl.gda.pg.eti.kask.javaee.enterprise.entities.Elf;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Forest;
import pl.gda.pg.eti.kask.javaee.enterprise.forests.ForestService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@RequestScoped
public class ElfConverter implements Converter {

    @EJB
    ForestService forestService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }

        Elf elf = forestService.findElf(Integer.parseInt(value));

        if (elf == null) {
            context.getExternalContext().setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
            context.responseComplete();
        }

        return elf;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Elf elf = (Elf) value;
        return elf.getId() != null ? Integer.toString(elf.getId()) : null;
    }
}
