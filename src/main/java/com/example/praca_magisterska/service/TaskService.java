package com.example.praca_magisterska.service;

import com.example.praca_magisterska.model.Task;
import com.example.praca_magisterska.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task create(Task task) {
        return taskRepository.save(
                new Task(
                        null,
                        task.title(),
                        task.description(),
                        task.status(),
                        task.createdAt(),
                        task.deadline(),
                        task.completedAt(),
                        task.userId(),
                        task.projectId()

                )
        );
    }

    public List<Task> findByProjectId(String projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id" + id));
    }

    public void deleteByProjectId(String projectId) {
        taskRepository.deleteByProjectId(projectId);
    }

    public Task update(String id, Task updated) {
        Task existing = findById(id);


        Task taskToSave = new Task(
                existing.id(),
                updated.title(),
                updated.description(),
                updated.status(),
                existing.createdAt(),
                updated.deadline(),
                updated.completedAt(),
                existing.userId(),
                existing.projectId()

        );
        return taskRepository.save(taskToSave);

    }

    public void delete(String id) {
        taskRepository.deleteById(id);
    }




}
