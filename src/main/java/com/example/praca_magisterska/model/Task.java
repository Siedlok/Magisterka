package com.example.praca_magisterska.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document(collection = "tasks")
public record Task(

        @Id
        String id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime deadline,
        LocalDateTime completedAt,
        String userId,
        String projectId

) {

}
