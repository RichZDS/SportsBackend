package com.zds.sports.service;

import com.zds.sports.convert.EquipmentMapper;
import com.zds.sports.dao.EquipmentRepository;
import com.zds.sports.domain.entity.EquipmentDO;
import com.zds.sports.model.dto.CreateEquipmentDTO;
import com.zds.sports.model.dto.UpdateEquipmentDTO;
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
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    @Transactional(readOnly = true)
    public Page<EquipmentDO> getEquipment(String keyword, Pageable pageable) {
        Specification<EquipmentDO> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                String likePattern = "%" + keyword + "%";
                predicates.add(cb.or(
                        cb.like(root.get("name"), likePattern),
                        cb.like(root.get("category"), likePattern),
                        cb.like(root.get("location"), likePattern)
                ));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return equipmentRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public EquipmentDO getEquipment(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found with id: " + id));
    }

    @Transactional
    public EquipmentDO createEquipment(CreateEquipmentDTO createEquipmentDTO) {
        EquipmentDO equipmentDO = equipmentMapper.toDO(createEquipmentDTO);
        return equipmentRepository.save(equipmentDO);
    }

    @Transactional
    public EquipmentDO updateEquipment(Long id, UpdateEquipmentDTO updateEquipmentDTO) {
        EquipmentDO equipmentDO = getEquipment(id);
        equipmentMapper.updateDO(updateEquipmentDTO, equipmentDO);
        return equipmentRepository.save(equipmentDO);
    }

    @Transactional
    public void deleteEquipment(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Equipment not found with id: " + id);
        }
        equipmentRepository.deleteById(id);
    }
}
