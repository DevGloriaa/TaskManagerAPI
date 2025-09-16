package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.repository.TaskRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarExportController {

    private final TaskRepository taskRepository;

    @GetMapping("/api/export/week.ics")
    public void exportNextWeek(HttpServletResponse response) throws IOException {
        LocalDate today = LocalDate.now();
        LocalDate weekLater = today.plusDays(7);

        List<Task> tasks = taskRepository.findByDueDateBetween(today, weekLater);

        StringBuilder ics = new StringBuilder();
        ics.append("BEGIN:VCALENDAR\n");
        ics.append("VERSION:2.0\n");
        ics.append("PRODID:-//Optimus Task Manager//EN\n");
        ics.append("CALSCALE:GREGORIAN\n");

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

        for (Task t : tasks) {
            LocalDateTime start = t.getDueDate().atTime(6, 0);
            LocalDateTime end = t.getDueDate().plusDays(1).atTime(0, 0);

            String dtStart = start.atZone(ZoneOffset.UTC).format(fmt);
            String dtEnd = end.atZone(ZoneOffset.UTC).format(fmt);
            String dtStamp = LocalDateTime.now().atZone(ZoneOffset.UTC).format(fmt);

            ics.append("BEGIN:VEVENT\n");
            ics.append("UID:").append(t.getId()).append("@optimus\n");
            ics.append("DTSTAMP:").append(dtStamp).append("\n");
            ics.append("DTSTART:").append(dtStart).append("\n");
            ics.append("DTEND:").append(dtEnd).append("\n");
            ics.append("SUMMARY:").append(t.getTitle()).append("\n");
            ics.append("DESCRIPTION:").append(t.getDescription()).append("\n");
            ics.append("END:VEVENT\n");
        }

        ics.append("END:VCALENDAR");

        response.setContentType("text/calendar; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=optimus-week.ics");
        response.getWriter().write(ics.toString());
    }
}
