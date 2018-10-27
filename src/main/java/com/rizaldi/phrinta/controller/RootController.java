package com.rizaldi.phrinta.controller;

import com.rizaldi.phrinta.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    private static final GrantedAuthority ADMIN_AUTHORITY = User.Role.ADMIN.toGrantedAuthority();

    @GetMapping("/")
    public String root(Authentication auth) {
        boolean isAdmin = auth.getAuthorities().contains(ADMIN_AUTHORITY);
        return isAdmin ? "redirect:/admin" : "redirect:/print";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Phrinta");
        return "HelloPage";
    }
}
