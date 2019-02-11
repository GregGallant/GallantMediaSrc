package gallantmedia.services.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PhoneFormatValidator implements ConstraintValidator<PhoneFormat, String> {

    private String phone_pattern;

    @Override
    public void initialize(PhoneFormat phoneFormat)
    {
        this.phone_pattern = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintContext)
    {

        if (phoneNumber == null) {
            return true; // Assuming this returns true because null is valid
        }

        // reg ex...
        Pattern pattern = Pattern.compile(this.phone_pattern);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches() || phoneNumber.equals("")) {
            return true;
        }

        return false;
    }

}