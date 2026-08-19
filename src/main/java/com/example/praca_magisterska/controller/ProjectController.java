package com.example.praca_magisterska.controller;

import com.example.praca_magisterska.model.Project;
import com.example.praca_magisterska.service.ProjectService;
import com.example.praca_magisterska.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping({"", "/"})
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "projects";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("project", new Project(
                null,
                "",
                null
        ));

        return "create-project";
    }

    @PostMapping
    public String createProject(@ModelAttribute Project project) {

        Project newProject = new Project(
                null,
                project.name(),
                LocalDateTime.now()
        );

        projectService.create(newProject);

        return "redirect:/projects";
    }

    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable String id) {

        taskService.deleteByProjectId(id);
        projectService.delete(id);

        return "redirect:/projects";
    }


    @GetMapping("/{id}")
    public String projectTasks(@PathVariable String id, Model model) {

        var tasks = taskService.findByProjectId(id);

        System.out.println("PROJECT ID = " + id);
        System.out.println("TASKS COUNT = " + tasks.size());

        model.addAttribute("tasks", tasks);
        model.addAttribute("projectId", id);
        model.addAttribute("project", projectService.findById(id));

        return "tasks";
    }


}