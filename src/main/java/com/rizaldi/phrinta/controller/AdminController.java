package com.rizaldi.phrinta.controller;

import com.rizaldi.phrinta.model.PrintJob;
import com.rizaldi.phrinta.service.PrintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    private final PrintService service;

    public AdminController(PrintService service) {
        this.service = service;
    }

    @GetMapping("queue")
    public String queueList(Model model) {
        List<PrintJob> printJobs = service.getAllJob();
        model.addAttribute("rows", printJobs);
        return "PrintQueue";
    }
}
