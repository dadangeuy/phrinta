package com.rizaldi.phrinta.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Data
@Document(collection = "print.job")
@CompoundIndexes({
        @CompoundIndex(def = "{'user.username':1}")
})
public class PrintJob {
    private static final DateFormat format = new SimpleDateFormat("dd MMMM yyyy, HH:mm");
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

    public String getCreatedAtDateFormat() {
        Date date = new Date(createdAt);
        return format.format(date);
    }

    public enum Status {
        UPLOADING, WAITING, REJECTED, PROCESS, DONE
    }
}
