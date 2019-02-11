package gallantmedia.services.validator;

//import java.util.List;
import javax.validation.Constraint;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy=PhoneFormatValidator.class)
public @interface PhoneFormat {

    String message() default "The phone number you entered is invalid.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
