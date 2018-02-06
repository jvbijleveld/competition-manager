package nl.vanbijleveld.cm.controller;

import java.lang.invoke.MethodHandles;

import nl.vanbijleveld.cm.auth.UserValidator;
import nl.vanbijleveld.cm.users.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    static final String CONTEXT_ROOT = "/user";

    @Autowired
    private UserValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @PutMapping(value = CONTEXT_ROOT)
    public ResponseEntity<?> saveUser(@ModelAttribute("user") @Validated User user, BindingResult result, Model model) {
        LOGGER.info("Creating new User {}", user.getEmail());
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
