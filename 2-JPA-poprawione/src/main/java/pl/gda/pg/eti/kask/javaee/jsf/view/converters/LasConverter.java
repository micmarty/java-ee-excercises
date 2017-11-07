package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import pl.gda.pg.eti.kask.javaee.jsf.ElfService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Las;

@ManagedBean
@RequestScoped
public class LasConverter implements Converter {

    @ManagedProperty("#{elfService}")
    private ElfService elfService;

    public void setElfService(ElfService elfService) {
        this.elfService = elfService;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ("---".equals(value)) {
            return null;
        }
        Las v = elfService.findLas(Integer.parseInt(value));
        return v;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "---";
        }
        String s = ((Las) value).getId() + "";
        return s;
    }
}
