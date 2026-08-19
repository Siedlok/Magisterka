package com.example.praca_magisterska.controller;


import com.example.praca_magisterska.model.Task;
import com.example.praca_magisterska.model.TaskStatus;
import com.example.praca_magisterska.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/test-data")
public class TestDataController {

    private final TaskService taskService;

    public TestDataController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/projects/{projectId}/tasks")
    @ResponseBody
    public String generateTasks(@PathVariable String projectId,
                                @RequestParam int count) {

        for (int i = 1; i <= count; i++) {
            TaskStatus status = switch (i % 3) {
                case 0 -> TaskStatus.DONE;
                case 1 -> TaskStatus.TODO;
                default -> TaskStatus.IN_PROGRESS;

            };

            LocalDateTime createdAt = LocalDateTime.now().minusDays(i % 30);
            LocalDateTime deadline = LocalDateTime.now().plusDays((i % 5) + 1);
            LocalDateTime completedAt = status == TaskStatus.DONE ? createdAt.plusDays((i % 5) + 1) : null;

            Task task = new Task(
                    null,
                    "Test task " + i,
                    "Generated test task for performance testing",
                    status,
                    createdAt,
                    deadline,
                    completedAt,
                    "performance test user",
                    projectId


            );
            taskService.create(task);
        }

        return "Generated" + count + "tasks for project" + projectId;


    }

    @DeleteMapping("/projects/{projectId}/tasks")
    @ResponseBody
    public String deleteTestTasks(@PathVariable String projectId) {
        taskService.deleteByProjectId(projectId);
        return "Deleted tasks for project " + projectId;
    }


}
