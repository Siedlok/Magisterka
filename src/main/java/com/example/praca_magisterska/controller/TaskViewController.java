package com.example.praca_magisterska.controller;

import com.example.praca_magisterska.model.Task;
import com.example.praca_magisterska.model.TaskStatus;
import com.example.praca_magisterska.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/tasks")
//@RequiredArgsConstructor
public class TaskViewController {

    private final TaskService taskService;

    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks";
    }

    @GetMapping("/new")
    public String createForm(
            @RequestParam String projectId,
            Model model,
            Principal principal) {

        String username = principal.getName();

        model.addAttribute(
                "task", new Task
                        (
                                null,
                                "",
                                "",
                                TaskStatus.TODO,
                                null,
                                null,
                                null,
                                username,
                                projectId
                        ));
        return "task-form";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task formTask,
                             Principal principal) {

        String loggedInUsername = principal.getName();

        LocalDateTime completedAt = formTask.status() == TaskStatus.DONE
                ? LocalDateTime.now()
                : null;

        Task taskToSave = new Task(
                null,
                formTask.title(),
                formTask.description(),
                formTask.status(),
                LocalDateTime.now(),
                formTask.deadline(),
                completedAt,
                loggedInUsername,
                formTask.projectId()
        );

        taskService.create(taskToSave);

        return "redirect:/projects/" + formTask.projectId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        Task task = taskService.findById(id);
        String projectId = task.projectId();

        taskService.delete(id);

        return "redirect:/projects/" + projectId;
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "task-form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id,
                         @ModelAttribute Task formTask) {

        Task existing = taskService.findById(id);

        Task taskToUpdate = buildTask(formTask, existing);

        taskService.update(id, taskToUpdate);

        return "redirect:/projects/" + taskToUpdate.projectId();
    }

    @PostMapping("/{id}/complete")
    public String completeTask(@PathVariable String id) {
        Task existing = taskService.findById(id);

        Task completeTask = new Task(
                existing.id(),
                existing.title(),
                existing.description(),
                TaskStatus.DONE,
                existing.createdAt(),
                existing.deadline(),
                LocalDateTime.now(),
                existing.userId(),
                existing.projectId()
        );

        taskService.update(id, completeTask);

        return "redirect:/projects/" + existing.projectId();
    }


    private Task buildTask(Task formTask, Task existing) {

        LocalDateTime completedAt = existing.completedAt();

        if (formTask.status() == TaskStatus.DONE && completedAt == null) {
            completedAt = LocalDateTime.now();
        }

        if (formTask.status() != TaskStatus.DONE) {
            completedAt = null;
        }
        LocalDateTime deadline = formTask.deadline() != null
                ? formTask.deadline()
                : existing.deadline();

        return new Task(
                existing.id(),
                formTask.title(),
                formTask.description(),
                formTask.status(),
                existing.createdAt(),
                deadline,
                completedAt,
                existing.userId(),
                existing.projectId()
        );
    }

}
