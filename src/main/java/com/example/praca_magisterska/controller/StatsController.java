package com.example.praca_magisterska.controller;

import com.example.praca_magisterska.model.Project;
import com.example.praca_magisterska.model.ProjectStats;
import com.example.praca_magisterska.service.ProjectService;
import com.example.praca_magisterska.service.StatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;
    private final ProjectService projectService;

    public StatsController(StatsService statsService, ProjectService projectService) {
        this.statsService = statsService;
        this.projectService = projectService;
    }

    @GetMapping
    public String projectStatistics(@RequestParam String projectId, Model model) {
        Project project = projectService.findById(projectId);
        ProjectStats stats = statsService.getProjectStats(projectId);

        model.addAttribute("project", project);
        model.addAttribute("stats", stats);
        model.addAttribute("projectId", projectId);

        return "stats";


    }


}