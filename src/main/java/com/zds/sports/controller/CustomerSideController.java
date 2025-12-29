package com.zds.sports.controller;

import com.zds.sports.logic.ConsumeRecordLogic;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.model.vo.RecordVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
@Tag(name = "Customer Side", description = "APIs for customer self-service")
public class CustomerSideController {

    private final ConsumeRecordLogic recordLogic;

    // TODO: In a real system, get customerId from security context
    private static final Long MOCK_CUSTOMER_ID = 1L;

    @GetMapping("/records")
    @Operation(summary = "Get my consumption records")
    public PageResultVO<RecordVO> getMyRecords(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end) {
        return recordLogic.getRecords(page, size, MOCK_CUSTOMER_ID, null, start, end);
    }
}
