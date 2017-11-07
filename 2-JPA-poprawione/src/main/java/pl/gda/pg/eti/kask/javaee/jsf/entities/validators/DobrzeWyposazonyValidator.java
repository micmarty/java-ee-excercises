package pl.gda.pg.eti.kask.javaee.jsf.entities.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.java.Log;

/**
 *
 * @author psysiu
 */
@Log
public class DobrzeWyposazonyValidator implements ConstraintValidator<DobrzeWyposazony, Integer> {

    //public static final String DATE_PATTERN = "yyyy-MM-dd";

    private Integer minLiczbaStrzal;

    @Override
    public void initialize(DobrzeWyposazony constraintAnnotation) {
        minLiczbaStrzal = Integer.parseInt(constraintAnnotation.MinLiczbaStrzal());
//        if (!constraintAnnotation.date().equals("")) {
//            try {
//                date = new SimpleDateFormat(DATE_PATTERN).parse(constraintAnnotation.date());
//            } catch (ParseException ex) {
//                log.log(Level.WARNING, ex.getMessage(), ex);
//                date = null;
//            }
//        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (minLiczbaStrzal < value) {
            return true;
        } else {
            return false;
        }
    }
}
