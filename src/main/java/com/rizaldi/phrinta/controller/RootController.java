package com.rizaldi.phrinta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:/print";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Phrinta");
        return "HelloPage";
    }
}
