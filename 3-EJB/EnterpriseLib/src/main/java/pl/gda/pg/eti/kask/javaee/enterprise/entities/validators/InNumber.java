package pl.gda.pg.eti.kask.javaee.enterprise.entities.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 *
 * @author psysiu
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InNumberValidator.class)
public @interface InNumber {
    
    String message() default "{pl.gda.pg.eti.kask.javaee.enterprise.entities.validators.InNumber.IN_PAST}";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String value() default "";
    
}
