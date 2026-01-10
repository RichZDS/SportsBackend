package com.zds.sports.logic;

import com.zds.sports.convert.MemberMapper;
import com.zds.sports.domain.entity.MemberDO;
import com.zds.sports.model.dto.CreateMemberDTO;
import com.zds.sports.model.dto.UpdateMemberDTO;
import com.zds.sports.model.vo.MemberVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberLogic {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public PageResultVO<MemberVO> getMembers(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<MemberDO> memberPage = memberService.getMembers(keyword, pageable);

        List<MemberVO> list = memberPage.getContent().stream()
                .map(memberMapper::toVO)
                .collect(Collectors.toList());

        return new PageResultVO<>(list, memberPage.getTotalElements(), page, size);
    }

    public MemberVO getMember(Long id) {
        MemberDO memberDO = memberService.getMember(id);
        return memberMapper.toVO(memberDO);
    }

    public MemberVO createMember(CreateMemberDTO createMemberDTO) {
        MemberDO memberDO = memberService.createMember(createMemberDTO);
        return memberMapper.toVO(memberDO);
    }

    public MemberVO updateMember(Long id, UpdateMemberDTO updateMemberDTO) {
        MemberDO memberDO = memberService.updateMember(id, updateMemberDTO);
        return memberMapper.toVO(memberDO);
    }

    public void deleteMember(Long id) {
        memberService.deleteMember(id);
    }
}
