package com.rizaldi.phrinta.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "print.job")
public class PrintJob {
    @Id
    private String id;
    @Indexed
    private long createdAt;
    @DBRef
    private User user;
    private String filename;
    private String gridFsName;
    @Indexed
    private Status status;

    public enum Status {
        UPLOAD, PENDING, DONE
    }
}
