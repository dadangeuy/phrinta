package com.rizaldi.phrinta.service;

import com.rizaldi.phrinta.model.PrintJob;
import com.rizaldi.phrinta.model.User;
import com.rizaldi.phrinta.repository.PrintJobRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PrintService {
    private final PrintJobRepository repository;
    private final GridFsTemplate gridFsTemplate;

    public PrintService(PrintJobRepository repository, GridFsTemplate gridFsTemplate) {
        this.repository = repository;
        this.gridFsTemplate = gridFsTemplate;
    }

    public PrintJob addJob(String username, MultipartFile file) throws IOException {
        // insert job first to get unique id from mongo
        PrintJob job = PrintJob.builder()
                .user(User.fromUsername(username))
                .filename(file.getOriginalFilename())
                .status(PrintJob.Status.UPLOAD)
                .createdAt(System.currentTimeMillis())
                .build();
        repository.insert(job);

        // save file
        String uniqueFilename = job.getId() + "-" + file.getOriginalFilename();
        gridFsTemplate.store(file.getInputStream(), uniqueFilename, file.getContentType());

        // update job
        job.setGridFsName(uniqueFilename);
        job.setStatus(PrintJob.Status.PENDING);
        repository.save(job);

        return job;
    }

    public List<PrintJob> findJob(String username) {
        return repository.findByUser_UsernameOrderByCreatedAtDesc(username);
    }

    public GridFsResource getFile(String gridFsName) {
        return gridFsTemplate.getResource(gridFsName);
    }

    private Query gridFsIdQuery(String id) {
        return Query.query(
                Criteria.where("_id").is(id));
    }
}
