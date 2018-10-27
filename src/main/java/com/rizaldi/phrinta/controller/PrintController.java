package com.rizaldi.phrinta.controller;

import com.rizaldi.phrinta.model.PrintJob;
import com.rizaldi.phrinta.service.PrintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("print")
public class PrintController {
    private static final Logger LOG = LoggerFactory.getLogger(PrintController.class);
    private final PrintService service;

    public PrintController(PrintService service) {
        this.service = service;
    }

    @GetMapping("")
    public String print(Authentication auth, Model model) {
        List<PrintJob> jobs = service.findJob(auth.getName());
        model.addAttribute("rows", jobs);
        return "PrintRequestPage";
    }

    @PostMapping("request")
    public String submitPrintRequest(Authentication auth, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        LOG.info("received: " + multipartFile.getOriginalFilename() + ", from: " + auth.getName());
        service.addJob(auth.getName(), multipartFile);
        return "redirect:/print";
    }

    @GetMapping(value = "download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> download(@PathVariable String id) {
        GridFsResource resource = service.getFile(id);
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=" + resource.getFilename())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
