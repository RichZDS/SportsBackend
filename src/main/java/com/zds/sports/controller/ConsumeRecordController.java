package com.zds.sports.controller;

import com.zds.sports.model.dto.CreateRecordDTO;
import com.zds.sports.model.dto.UpdateRecordDTO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.model.vo.RecordVO;
import com.zds.sports.service.ConsumeRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
@Tag(name = "Consume Record Management", description = "APIs for managing consumption records")
public class ConsumeRecordController {

    private final ConsumeRecordService consumeRecordService;

    @GetMapping
    @Operation(summary = "Get consumption records with pagination and search")
    public PageResultVO<RecordVO> getRecords(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end) {
        return consumeRecordService.getRecords(page, size, customerId, category, keyword, start, end);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new consumption record")
    public RecordVO createRecord(@Valid @RequestBody CreateRecordDTO createRecordDTO) {
        return consumeRecordService.createRecord(createRecordDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing consumption record")
    public RecordVO updateRecord(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRecordDTO updateRecordDTO) {
        return consumeRecordService.updateRecord(id, updateRecordDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a consumption record")
    public void deleteRecord(@PathVariable Long id) {
        consumeRecordService.deleteRecord(id);
    }
}
