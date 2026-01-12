package com.zds.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zds.sports.domain.entity.ConsumeRecordDO;
import com.zds.sports.model.dto.CreateRecordDTO;
import com.zds.sports.model.dto.UpdateRecordDTO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.model.vo.RecordVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ConsumeRecordService extends IService<ConsumeRecordDO> {
    PageResultVO<RecordVO> getRecords(int page, int size, String keyword);
    PageResultVO<RecordVO> getRecords(int page, int size, Long customerId, String keyword, LocalDateTime start, LocalDateTime end);
    RecordVO createRecord(CreateRecordDTO createRecordDTO);
    RecordVO updateRecord(Long id, UpdateRecordDTO updateRecordDTO);
    void deleteRecord(Long id);
    List<Map<String, Object>> getMonthlyStats(LocalDateTime start, LocalDateTime end);
    List<Map<String, Object>> getCategoryStats(LocalDateTime start, LocalDateTime end);
}
