package nl.vanbijleveld.cm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebsiteController {

    @RequestMapping(value = "/csrf")
    public String infoPage(Model model) {
        model.addAttribute("appName", "Competition Manager");
        model.addAttribute("appVersion", "0.0.1-SNAPSHOT");
        return "csrf";
    }

    @RequestMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "Competition Manager!");
        return "login";
    }

    @RequestMapping(value = "/register")
    public String registerPage(Model model) {
        model.addAttribute("title", "Competition Manager!");
        return "register";
    }

}
