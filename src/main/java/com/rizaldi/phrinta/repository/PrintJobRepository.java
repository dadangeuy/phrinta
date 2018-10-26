package com.rizaldi.phrinta.repository;

import com.rizaldi.phrinta.model.PrintJob;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrintJobRepository extends MongoRepository<PrintJob, String> {
    List<PrintJob> findByUser_UsernameOrderByCreatedAtDesc(String user_username);
}
