package pl.gda.pg.eti.kask.javaee.jsf.view.converters;
import pl.gda.pg.eti.kask.javaee.jsf.KatalogService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Elf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

@ManagedBean
@RequestScoped
@FacesConverter(value="lukConverter")
public class LukConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == "Drewniany")
            return (Object)Elf.RodzajLuku.DREWNIANY;
        else if(value == "Leszczynowy")
            return (Object)Elf.RodzajLuku.LESZCZYNOWY;
        else if(value == "Jesionowy")
            return (Object)Elf.RodzajLuku.JESIONOWY;
        else if(value == "Karbonowy")
            return (Object)Elf.RodzajLuku.KARBONOWY;
        return (Object)Elf.RodzajLuku.DREWNIANY;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if((Elf.RodzajLuku)value == Elf.RodzajLuku.DREWNIANY)
            return "Drewniany";
        else if((Elf.RodzajLuku)value == Elf.RodzajLuku.LESZCZYNOWY)
            return "Leszczynowy";
        else if((Elf.RodzajLuku)value == Elf.RodzajLuku.JESIONOWY)
            return "Jesionowy";
        else if((Elf.RodzajLuku)value == Elf.RodzajLuku.KARBONOWY)
            return "Karbonowy";
        return "Drewniany";
    }
}