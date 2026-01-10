package com.zds.sports.controller;

import com.zds.sports.logic.MemberLogic;
import com.zds.sports.model.dto.CreateMemberDTO;
import com.zds.sports.model.dto.UpdateMemberDTO;
import com.zds.sports.model.vo.MemberVO;
import com.zds.sports.model.vo.PageResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member Management", description = "APIs for managing members")
public class MemberController {

    private final MemberLogic memberLogic;

    @GetMapping
    @Operation(summary = "Get members list with pagination and search")
    public PageResultVO<MemberVO> getMembers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        return memberLogic.getMembers(page, size, keyword);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new member")
    public MemberVO createMember(@Valid @RequestBody CreateMemberDTO createMemberDTO) {
        return memberLogic.createMember(createMemberDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing member")
    public MemberVO updateMember(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMemberDTO updateMemberDTO) {
        return memberLogic.updateMember(id, updateMemberDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a member")
    public void deleteMember(@PathVariable Long id) {
        memberLogic.deleteMember(id);
    }
}
