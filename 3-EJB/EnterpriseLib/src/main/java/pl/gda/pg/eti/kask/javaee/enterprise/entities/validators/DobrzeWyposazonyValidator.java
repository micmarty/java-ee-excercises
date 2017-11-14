package pl.gda.pg.eti.kask.javaee.enterprise.entities.validators;

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


    private Integer minValue;

    @Override
    public void initialize(DobrzeWyposazony constraintAnnotation) {
        if (!constraintAnnotation.value().equals("")) {
            try {
                minValue = Integer.parseInt(constraintAnnotation.value());
            } catch (Exception ex) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                minValue = null;
            }
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (minValue != null) {
            return minValue < value;
        } else {
            return false;
        }
    }
}
