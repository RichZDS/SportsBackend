package com.zds.sports.controller;

import com.zds.sports.service.ConsumeRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@Tag(name = "Statistics", description = "APIs for statistics")
public class StatsController {

    private final ConsumeRecordService consumeRecordService;

    @GetMapping("/monthly")
    @Operation(summary = "Get monthly consumption statistics")
    public List<Map<String, Object>> getMonthlyStats(@RequestParam(required = false) Integer year) {
        int targetYear = year != null ? year : LocalDate.now().getYear();
        LocalDateTime start = LocalDateTime.of(targetYear, 1, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(targetYear, 12, 31, 23, 59, 59);
        return consumeRecordService.getMonthlyStats(start, end);
    }

    @GetMapping("/category")
    @Operation(summary = "Get consumption statistics by category")
    public List<Map<String, Object>> getCategoryStats(
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end) {
        if (start == null) {
            start = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN);
        }
        if (end == null) {
            end = LocalDateTime.now();
        }
        return consumeRecordService.getCategoryStats(start, end);
    }
}
