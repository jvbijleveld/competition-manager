package nl.vanbijleveld.cm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebsiteController {

    @RequestMapping(value = "/info")
    public String homePage(Model model) {
        model.addAttribute("appName", "Competition Manager");

        return "info";
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
