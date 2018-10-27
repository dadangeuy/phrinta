package com.rizaldi.phrinta.controller;

import com.rizaldi.phrinta.config.WebConfig;
import com.rizaldi.phrinta.model.PrintJob;
import com.rizaldi.phrinta.model.User;
import com.rizaldi.phrinta.service.PrintService;
import com.rizaldi.phrinta.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final String title;
    private final String icon;
    private final PrintService printService;
    private final UserService userService;

    public AdminController(WebConfig config, PrintService printService, UserService userService) {
        this.title = config.getTitle();
        this.icon = config.getIcon();
        this.printService = printService;
        this.userService = userService;
    }

    @GetMapping("")
    public String rootView() {
        return "redirect:/admin/queue";
    }

    @GetMapping("queue")
    public String queueList(Model model) {
        model.addAttribute("title", title);
        model.addAttribute("icon", icon);
        List<PrintJob> printJobs = printService.getAllJob();
        model.addAttribute("rows", printJobs);
        model.addAttribute("statuses", PrintJob.Status.values());
        return "PrintQueuePage";
    }

    @GetMapping("user")
    public String manageUser(Model model) {
        model.addAttribute("title", title);
        model.addAttribute("icon", icon);
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "ManageUserPage";
    }
}
