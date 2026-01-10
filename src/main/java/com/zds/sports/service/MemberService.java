package com.zds.sports.service;

import com.zds.sports.convert.MemberMapper;
import com.zds.sports.dao.MemberRepository;
import com.zds.sports.domain.entity.MemberDO;
import com.zds.sports.model.dto.CreateMemberDTO;
import com.zds.sports.model.dto.UpdateMemberDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional(readOnly = true)
    public Page<MemberDO> getMembers(String keyword, Pageable pageable) {
        Specification<MemberDO> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                String likePattern = "%" + keyword + "%";
                predicates.add(cb.or(
                        cb.like(root.get("name"), likePattern),
                        cb.like(root.get("phone"), likePattern),
                        cb.like(root.get("email"), likePattern)
                ));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return memberRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public MemberDO getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));
    }

    @Transactional
    public MemberDO createMember(CreateMemberDTO createMemberDTO) {
        MemberDO memberDO = memberMapper.toDO(createMemberDTO);
        return memberRepository.save(memberDO);
    }

    @Transactional
    public MemberDO updateMember(Long id, UpdateMemberDTO updateMemberDTO) {
        MemberDO memberDO = getMember(id);
        memberMapper.updateDO(updateMemberDTO, memberDO);
        return memberRepository.save(memberDO);
    }

    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new EntityNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}
