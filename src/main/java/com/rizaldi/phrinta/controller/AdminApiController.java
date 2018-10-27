package com.rizaldi.phrinta.controller;

import com.rizaldi.phrinta.model.PrintJob;
import com.rizaldi.phrinta.service.PrintService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {
    private final PrintService service;

    public AdminApiController(PrintService service) {
        this.service = service;
    }

    @PostMapping("job/update")
    public PrintJob update(@RequestParam String jobId, @RequestParam PrintJob.Status status) {
        return service.updateJob(jobId, status);
    }
}
