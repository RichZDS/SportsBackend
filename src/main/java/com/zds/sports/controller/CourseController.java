package com.zds.sports.controller;

import com.zds.sports.logic.CourseLogic;
import com.zds.sports.model.dto.CreateCourseDTO;
import com.zds.sports.model.dto.UpdateCourseDTO;
import com.zds.sports.model.vo.CourseVO;
import com.zds.sports.model.vo.PageResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Tag(name = "Course Management", description = "APIs for managing courses")
public class CourseController {

    private final CourseLogic courseLogic;

    @GetMapping
    @Operation(summary = "Get courses list with pagination and search")
    public PageResultVO<CourseVO> getCourses(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long coachId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword) {
        return courseLogic.getCourses(page, size, coachId, status, keyword);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new course")
    public CourseVO createCourse(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        return courseLogic.createCourse(createCourseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing course")
    public CourseVO updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCourseDTO updateCourseDTO) {
        return courseLogic.updateCourse(id, updateCourseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a course")
    public void deleteCourse(@PathVariable Long id) {
        courseLogic.deleteCourse(id);
    }
}
