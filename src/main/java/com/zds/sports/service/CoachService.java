package com.zds.sports.service;

import com.zds.sports.convert.CoachMapper;
import com.zds.sports.dao.CoachRepository;
import com.zds.sports.domain.entity.CoachDO;
import com.zds.sports.model.dto.CreateCoachDTO;
import com.zds.sports.model.dto.UpdateCoachDTO;
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
public class CoachService {

    private final CoachRepository coachRepository;
    private final CoachMapper coachMapper;

    @Transactional(readOnly = true)
    public Page<CoachDO> getCoaches(String keyword, Pageable pageable) {
        Specification<CoachDO> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                String likePattern = "%" + keyword + "%";
                predicates.add(cb.or(
                        cb.like(root.get("name"), likePattern),
                        cb.like(root.get("phone"), likePattern),
                        cb.like(root.get("specialty"), likePattern)
                ));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return coachRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public CoachDO getCoach(Long id) {
        return coachRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coach not found with id: " + id));
    }

    @Transactional
    public CoachDO createCoach(CreateCoachDTO createCoachDTO) {
        CoachDO coachDO = coachMapper.toDO(createCoachDTO);
        return coachRepository.save(coachDO);
    }

    @Transactional
    public CoachDO updateCoach(Long id, UpdateCoachDTO updateCoachDTO) {
        CoachDO coachDO = getCoach(id);
        coachMapper.updateDO(updateCoachDTO, coachDO);
        return coachRepository.save(coachDO);
    }

    @Transactional
    public void deleteCoach(Long id) {
        if (!coachRepository.existsById(id)) {
            throw new EntityNotFoundException("Coach not found with id: " + id);
        }
        coachRepository.deleteById(id);
    }
}
