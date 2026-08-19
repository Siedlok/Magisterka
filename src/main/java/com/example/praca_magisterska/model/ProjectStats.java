package com.example.praca_magisterska.model;

public record ProjectStats(

        long totalTasks,
        long todoTasks,
        long inProgressTasks,
        long doneTasks,
        long overdueTasks,
        long tasksWithoutDeadline,
        double completionPercent,
        long averagePlannedHours,
        long minPlannedHours,
        long maxPlannedHours


) {
}
