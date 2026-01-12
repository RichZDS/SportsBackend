package com.zds.sports.controller;

import com.zds.sports.model.dto.CreateCourseBookingDTO;
import com.zds.sports.model.dto.UpdateCourseBookingDTO;
import com.zds.sports.model.vo.CourseBookingVO;
import com.zds.sports.service.CourseBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-bookings")
@RequiredArgsConstructor
@Tag(name = "Course Booking", description = "APIs for managing course bookings")
public class CourseBookingController {

    private final CourseBookingService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a course booking")
    public CourseBookingVO create(@Valid @RequestBody CreateCourseBookingDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a course booking")
    public CourseBookingVO update(@PathVariable Long id, @Valid @RequestBody UpdateCourseBookingDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a course booking")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}

