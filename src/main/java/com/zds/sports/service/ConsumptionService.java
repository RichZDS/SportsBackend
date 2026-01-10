package com.zds.sports.service;

import com.zds.sports.convert.ConsumptionMapper;
import com.zds.sports.dao.ConsumptionRepository;
import com.zds.sports.domain.entity.ConsumptionDO;
import com.zds.sports.model.dto.CreateConsumptionDTO;
import com.zds.sports.model.dto.UpdateConsumptionDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final ConsumptionMapper consumptionMapper;

    @Transactional(readOnly = true)
    public Page<ConsumptionDO> getConsumptions(Long memberId, String category, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        Specification<ConsumptionDO> spec = (root, query, cb) -> {
            if (!Long.class.equals(query.getResultType())) {
                root.fetch("member", JoinType.LEFT);
                query.distinct(true);
            }
            List<Predicate> predicates = new ArrayList<>();
            if (memberId != null) {
                predicates.add(cb.equal(root.get("memberId"), memberId));
            }
            if (StringUtils.hasText(category)) {
                predicates.add(cb.equal(root.get("category"), category));
            }
            if (start != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("paidAt"), start));
            }
            if (end != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("paidAt"), end));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return consumptionRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public ConsumptionDO getConsumption(Long id) {
        return consumptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consumption not found with id: " + id));
    }

    @Transactional
    public ConsumptionDO createConsumption(CreateConsumptionDTO createConsumptionDTO) {
        ConsumptionDO consumptionDO = consumptionMapper.toDO(createConsumptionDTO);
        return consumptionRepository.save(consumptionDO);
    }

    @Transactional
    public ConsumptionDO updateConsumption(Long id, UpdateConsumptionDTO updateConsumptionDTO) {
        ConsumptionDO consumptionDO = getConsumption(id);
        consumptionMapper.updateDO(updateConsumptionDTO, consumptionDO);
        return consumptionRepository.save(consumptionDO);
    }

    @Transactional
    public void deleteConsumption(Long id) {
        if (!consumptionRepository.existsById(id)) {
            throw new EntityNotFoundException("Consumption not found with id: " + id);
        }
        consumptionRepository.deleteById(id);
    }
}
