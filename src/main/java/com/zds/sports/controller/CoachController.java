package com.zds.sports.controller;

import com.zds.sports.logic.CoachLogic;
import com.zds.sports.model.dto.CreateCoachDTO;
import com.zds.sports.model.dto.UpdateCoachDTO;
import com.zds.sports.model.vo.CoachVO;
import com.zds.sports.model.vo.PageResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coaches")
@RequiredArgsConstructor
@Tag(name = "Coach Management", description = "APIs for managing coaches")
public class CoachController {

    private final CoachLogic coachLogic;

    @GetMapping
    @Operation(summary = "Get coaches list with pagination and search")
    public PageResultVO<CoachVO> getCoaches(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        return coachLogic.getCoaches(page, size, keyword);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new coach")
    public CoachVO createCoach(@Valid @RequestBody CreateCoachDTO createCoachDTO) {
        return coachLogic.createCoach(createCoachDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing coach")
    public CoachVO updateCoach(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCoachDTO updateCoachDTO) {
        return coachLogic.updateCoach(id, updateCoachDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a coach")
    public void deleteCoach(@PathVariable Long id) {
        coachLogic.deleteCoach(id);
    }
}
