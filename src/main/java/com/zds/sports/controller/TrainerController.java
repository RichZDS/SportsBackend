package com.zds.sports.controller;

import com.zds.sports.model.dto.CreateTrainerDTO;
import com.zds.sports.model.dto.UpdateTrainerDTO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.model.vo.TrainerVO;
import com.zds.sports.service.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
@Tag(name = "Trainer Management", description = "APIs for managing trainers")
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping
    @Operation(summary = "Get trainers list with pagination and search")
    public PageResultVO<TrainerVO> getTrainers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        return trainerService.getTrainers(page, size, keyword);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new trainer")
    public TrainerVO createTrainer(@Valid @RequestBody CreateTrainerDTO createTrainerDTO) {
        return trainerService.createTrainer(createTrainerDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing trainer")
    public TrainerVO updateTrainer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTrainerDTO updateTrainerDTO) {
        return trainerService.updateTrainer(id, updateTrainerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a trainer")
    public void deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
    }
}


