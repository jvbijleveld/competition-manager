package nl.vanbijleveld.cm.validate;

import nl.vanbijleveld.cm.player.Player;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PlayerValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Player.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Player p = (Player) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        if ((p.getEmail() == null) || p.getEmail().length() < 5) {
            errors.rejectValue("email", "invalid emailadres");
        }
    }

}
