package com.zds.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.sports.convert.RecordMapStruct;
import com.zds.sports.dao.ConsumeRecordMapper;
import com.zds.sports.domain.entity.ConsumeRecordDO;
import com.zds.sports.model.dto.CreateRecordDTO;
import com.zds.sports.model.dto.UpdateRecordDTO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.model.vo.RecordVO;
import com.zds.sports.service.ConsumeRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsumeRecordServiceImpl extends ServiceImpl<ConsumeRecordMapper, ConsumeRecordDO> implements ConsumeRecordService {

    private final RecordMapStruct recordMapStruct;

    @Override
    public PageResultVO<RecordVO> getRecords(int page, int size, String keyword) {
        return getRecords(page, size, null, null, keyword, null, null);
    }

    @Override
    public PageResultVO<RecordVO> getRecords(int page, int size, Long customerId, String keyword, LocalDateTime start, LocalDateTime end) {
        return getRecords(page, size, customerId, null, keyword, start, end);
    }

    @Override
    public PageResultVO<RecordVO> getRecords(int page, int size, Long customerId, String category, String keyword, LocalDateTime start, LocalDateTime end) {
        Page<ConsumeRecordDO> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<ConsumeRecordDO> wrapper = new LambdaQueryWrapper<>();
        
        if (customerId != null) {
            wrapper.eq(ConsumeRecordDO::getCustomerId, customerId);
        }
        
        if (StringUtils.hasText(category)) {
            wrapper.eq(ConsumeRecordDO::getCategory, category);
        }
        
        if (StringUtils.hasText(keyword)) {
            wrapper.and(w -> w.like(ConsumeRecordDO::getCategory, keyword)
                    .or().like(ConsumeRecordDO::getRemark, keyword));
        }
        
        if (start != null) {
            wrapper.ge(ConsumeRecordDO::getPaidAt, start);
        }
        
        if (end != null) {
            wrapper.le(ConsumeRecordDO::getPaidAt, end);
        }

        Page<ConsumeRecordDO> result = this.page(pageParam, wrapper);
        List<RecordVO> vos = result.getRecords().stream()
                .map(recordMapStruct::toVO)
                .collect(Collectors.toList());
        return new PageResultVO<>(result.getTotal(), result.getSize(), result.getCurrent(), result.getPages(), vos);
    }

    @Override
    public RecordVO createRecord(CreateRecordDTO createRecordDTO) {
        ConsumeRecordDO recordDO = recordMapStruct.toDO(createRecordDTO);
        this.save(recordDO);
        return recordMapStruct.toVO(recordDO);
    }

    @Override
    public RecordVO updateRecord(Long id, UpdateRecordDTO updateRecordDTO) {
        ConsumeRecordDO recordDO = this.getById(id);
        if (recordDO == null) {
            throw new RuntimeException("Record not found");
        }
        recordMapStruct.updateDO(updateRecordDTO, recordDO);
        this.updateById(recordDO);
        return recordMapStruct.toVO(recordDO);
    }

    @Override
    public void deleteRecord(Long id) {
        this.removeById(id);
    }

    @Override
    public List<Map<String, Object>> getMonthlyStats(LocalDateTime start, LocalDateTime end) {
        // MySQL 原生 MONTH() 函数
        QueryWrapper<ConsumeRecordDO> wrapper = new QueryWrapper<>();
        wrapper.select("MONTH(paid_at) as month", "SUM(amount) as value")
                .ge("paid_at", start)
                .le("paid_at", end)
                .groupBy("MONTH(paid_at)");
        return this.listMaps(wrapper);
    }

    @Override
    public List<Map<String, Object>> getCategoryStats(LocalDateTime start, LocalDateTime end) {
        QueryWrapper<ConsumeRecordDO> wrapper = new QueryWrapper<>();
        wrapper.select("category", "SUM(amount) as value")
                .ge("paid_at", start)
                .le("paid_at", end)
                .groupBy("category");
        return this.listMaps(wrapper);
    }
}
