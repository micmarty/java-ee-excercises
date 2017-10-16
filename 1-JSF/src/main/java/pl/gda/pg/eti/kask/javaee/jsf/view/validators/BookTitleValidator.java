package pl.gda.pg.eti.kask.javaee.jsf.view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

/**
 *
 * @author psysiu
 */
@ManagedBean
@RequestScoped
public class BookTitleValidator implements Validator {

//    private static final String PATTERN = "^\\p{Lu}(\\p{L}|\\p{N})*([ ](\\p{L}|\\p{N})*)*$";
    private static final Integer lowRange = 0;
    private static final Integer highRange = 100000;
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        //if (value instanceof Integer) {
            Integer liczbaDrzew = (Integer) value;
            if (liczbaDrzew < lowRange || liczbaDrzew > highRange) {
                throw new ValidatorException(new FacesMessage("Niepoprawna ilość drzew"));
            }
        //} else {
         //   throw new ValidatorException(new FacesMessage("Zły obiekt."));
        //}
    }
    
}
