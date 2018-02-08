package nl.vanbijleveld.cm.auth;

import nl.vanbijleveld.cm.api.UserService;
import nl.vanbijleveld.cm.users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() < 5 || user.getEmail().length() > 257) {
            errors.rejectValue("email", "Username cannot be empty");
        }
        if (userService.findByUsername(user.getEmail()) != null) {
            errors.rejectValue("email", "Username already in use");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Password not accepted");
        }

    }
}
