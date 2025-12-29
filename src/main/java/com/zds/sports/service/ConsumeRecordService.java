package com.zds.sports.service;

import com.zds.sports.dao.ConsumeRecordRepository;
import com.zds.sports.domain.entity.ConsumeRecordDO;
import com.zds.sports.model.dto.CreateRecordDTO;
import com.zds.sports.model.dto.UpdateRecordDTO;
import com.zds.sports.convert.RecordMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsumeRecordService {

    private final ConsumeRecordRepository recordRepository;
    private final RecordMapper recordMapper;

    @Transactional(readOnly = true)
    public Page<ConsumeRecordDO> getRecords(Long customerId, String category, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        Specification<ConsumeRecordDO> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (customerId != null) {
                predicates.add(cb.equal(root.get("customerId"), customerId));
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
        return recordRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public ConsumeRecordDO getRecord(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + id));
    }

    @Transactional
    public ConsumeRecordDO createRecord(CreateRecordDTO createRecordDTO) {
        ConsumeRecordDO recordDO = recordMapper.toDO(createRecordDTO);
        return recordRepository.save(recordDO);
    }

    @Transactional
    public ConsumeRecordDO updateRecord(Long id, UpdateRecordDTO updateRecordDTO) {
        ConsumeRecordDO recordDO = getRecord(id);
        recordMapper.updateDO(updateRecordDTO, recordDO);
        return recordRepository.save(recordDO);
    }

    @Transactional
    public void deleteRecord(Long id) {
        if (!recordRepository.existsById(id)) {
            throw new EntityNotFoundException("Record not found with id: " + id);
        }
        recordRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getCategoryStats(LocalDateTime start, LocalDateTime end) {
        return recordRepository.sumAmountByCategory(start, end);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getMonthlyStats(LocalDateTime start, LocalDateTime end) {
        return recordRepository.sumAmountByMonth(start, end);
    }
}
