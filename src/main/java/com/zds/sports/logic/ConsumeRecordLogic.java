package com.zds.sports.logic;

import com.zds.sports.domain.entity.ConsumeRecordDO;
import com.zds.sports.model.dto.CreateRecordDTO;
import com.zds.sports.model.dto.UpdateRecordDTO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.model.vo.RecordVO;
import com.zds.sports.service.ConsumeRecordService;
import com.zds.sports.convert.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConsumeRecordLogic {

    private final ConsumeRecordService recordService;
    private final RecordMapper recordMapper;

    public PageResultVO<RecordVO> getRecords(int page, int size, Long customerId, String category, LocalDateTime start, LocalDateTime end) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "paidAt"));
        Page<ConsumeRecordDO> recordPage = recordService.getRecords(customerId, category, start, end, pageable);
        
        List<RecordVO> list = recordPage.getContent().stream()
                .map(recordMapper::toVO)
                .collect(Collectors.toList());
        
        return new PageResultVO<>(list, recordPage.getTotalElements(), page, size);
    }

    public RecordVO getRecord(Long id) {
        ConsumeRecordDO recordDO = recordService.getRecord(id);
        return recordMapper.toVO(recordDO);
    }

    public RecordVO createRecord(CreateRecordDTO createRecordDTO) {
        ConsumeRecordDO recordDO = recordService.createRecord(createRecordDTO);
        return recordMapper.toVO(recordDO);
    }

    public RecordVO updateRecord(Long id, UpdateRecordDTO updateRecordDTO) {
        ConsumeRecordDO recordDO = recordService.updateRecord(id, updateRecordDTO);
        return recordMapper.toVO(recordDO);
    }

    public void deleteRecord(Long id) {
        recordService.deleteRecord(id);
    }

    public List<Map<String, Object>> getCategoryStats(LocalDateTime start, LocalDateTime end) {
        return recordService.getCategoryStats(start, end);
    }

    public List<Map<String, Object>> getMonthlyStats(LocalDateTime start, LocalDateTime end) {
        return recordService.getMonthlyStats(start, end);
    }
}
