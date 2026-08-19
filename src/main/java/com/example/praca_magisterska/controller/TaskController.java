package com.example.praca_magisterska.controller;

import com.example.praca_magisterska.model.Task;
import com.example.praca_magisterska.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @GetMapping
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @PutMapping("/{id}")
    public Task update(
            @PathVariable String id,
            @RequestBody Task task

    ) {
        return taskService.update(id, task);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable String id) {
        return taskService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        taskService.delete(id);
    }


}
