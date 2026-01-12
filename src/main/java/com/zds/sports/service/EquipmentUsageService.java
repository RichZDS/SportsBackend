package com.zds.sports.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.sports.convert.EquipmentUsageMapStruct;
import com.zds.sports.dao.EquipmentUsageMapper;
import com.zds.sports.domain.entity.EquipmentUsageDO;
import com.zds.sports.model.dto.CreateEquipmentUsageDTO;
import com.zds.sports.model.dto.UpdateEquipmentUsageDTO;
import com.zds.sports.model.vo.EquipmentUsageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentUsageService extends ServiceImpl<EquipmentUsageMapper, EquipmentUsageDO> {
    private final EquipmentUsageMapStruct mapper;

    public EquipmentUsageVO create(CreateEquipmentUsageDTO dto) {
        EquipmentUsageDO usage = mapper.toDO(dto);
        usage.setStatus("USING");
        this.save(usage);
        return mapper.toVO(usage);
    }

    public EquipmentUsageVO update(Long id, UpdateEquipmentUsageDTO dto) {
        EquipmentUsageDO existing = this.getById(id);
        mapper.updateDO(dto, existing);
        this.updateById(existing);
        return mapper.toVO(existing);
    }

    public void deleteById(Long id) {
        this.removeById(id);
    }
}

