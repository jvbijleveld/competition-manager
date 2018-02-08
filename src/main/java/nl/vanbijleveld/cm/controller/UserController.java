package nl.vanbijleveld.cm.controller;

import java.lang.invoke.MethodHandles;

import nl.vanbijleveld.cm.api.UserService;
import nl.vanbijleveld.cm.users.User;
import nl.vanbijleveld.cm.validate.UserValidator;

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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    static final String CONTEXT_ROOT = "/user";

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private UserService userService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @PostMapping(value = CONTEXT_ROOT)
    public ResponseEntity<?> saveUser(@ModelAttribute("user") @Validated User user, BindingResult result, Model model) {
        LOGGER.info("Creating new User {}", user.getEmail());
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        userService.save(user);
        
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
