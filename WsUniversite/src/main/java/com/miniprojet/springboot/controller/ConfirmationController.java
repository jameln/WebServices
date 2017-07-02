package com.miniprojet.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConfirmationController {
    @RequestMapping(value = { "", "/confirmation", "/home" })
    public String index(Model model) {
        model.addAttribute("activePage", "home");
        return "confirmation";
    }
}
