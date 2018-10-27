package com.rizaldi.phrinta.controller;

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
    private final PrintService printService;
    private final UserService userService;

    public AdminController(PrintService printService, UserService userService) {
        this.printService = printService;
        this.userService = userService;
    }

    @GetMapping("")
    public String rootView() {
        return "redirect:/admin/queue";
    }

    @GetMapping("queue")
    public String queueList(Model model) {
        List<PrintJob> printJobs = printService.getAllJob();
        model.addAttribute("rows", printJobs);
        model.addAttribute("statuses", PrintJob.Status.values());
        return "PrintQueuePage";
    }

    @GetMapping("user")
    public String manageUser(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "ManageUserPage";
    }
}
