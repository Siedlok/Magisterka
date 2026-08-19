package com.example.praca_magisterska.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "projects")
public record Project(

        @Id
        String id,
        String name,
        LocalDateTime createdAt


) {
}
