package com.example.praca_magisterska.service;


import com.example.praca_magisterska.model.ProjectStats;
import com.example.praca_magisterska.model.Task;
import com.example.praca_magisterska.model.TaskStatus;
import com.example.praca_magisterska.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatsService {

    private final TaskRepository taskRepository;

    public StatsService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ProjectStats getProjectStats(String projectId) {

        List<Task> tasks = taskRepository.findByProjectId(projectId);

        long total = tasks.size();

        long todo = tasks.stream()
                .filter(task -> TaskStatus.TODO.equals(task.status()))
                .count();

        long inProgress = tasks.stream()
                .filter(task -> TaskStatus.IN_PROGRESS.equals(task.status()))
                .count();

        long done = tasks.stream()
                .filter(task -> TaskStatus.DONE.equals(task.status()))
                .count();


        LocalDateTime now = LocalDateTime.now();

        long overdue = tasks.stream()
                .filter(task -> task.deadline() != null)
                .filter(task -> task.deadline().isBefore(now))
                .filter(task -> task.status() != TaskStatus.DONE)
                .count();


        long withoutDeadline = tasks.stream()
                .filter(task -> task.deadline() == null)
                .count();

        double completionPercent = total == 0 ? 0 : (done * 100.0) / total;

        List<Long> plannedHours = tasks.stream()
                .filter(task -> task.createdAt() != null)
                .filter(task -> task.deadline() != null)
                .map(task -> Duration.between(task.createdAt(), task.deadline()).toHours())
                .filter(hours -> hours >= 0)
                .toList();

        long averagePlannedHours = plannedHours.isEmpty() ? 0 : Math.round(plannedHours.stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0));

        long minPlannedHours = plannedHours.isEmpty()
                ? 0
                : plannedHours.stream()
                .mapToLong(Long::longValue)
                .min()
                .orElse(0);

        long maxPlannedHours = plannedHours.isEmpty()
                ? 0
                : plannedHours.stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(0);

        return new ProjectStats(

                total,
                todo,
                inProgress,
                done,
                overdue,
                withoutDeadline,
                completionPercent,
                averagePlannedHours,
                minPlannedHours,
                maxPlannedHours

        );


    }

}
