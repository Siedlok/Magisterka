package com.example.praca_magisterska.repository;

import com.example.praca_magisterska.model.Task;
import com.example.praca_magisterska.model.TaskStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByUserId(String userId);

    List<Task> findByProjectId(String projectId);

    long countByProjectId(String projectId);

    long countByProjectIdAndStatus(String projectId, TaskStatus status);

    void deleteByProjectId(String projectId);

}
