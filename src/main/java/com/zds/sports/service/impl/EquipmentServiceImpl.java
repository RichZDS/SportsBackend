package com.zds.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.sports.convert.EquipmentMapStruct;
import com.zds.sports.dao.EquipmentMapper;
import com.zds.sports.domain.entity.EquipmentDO;
import com.zds.sports.model.dto.CreateEquipmentDTO;
import com.zds.sports.model.dto.UpdateEquipmentDTO;
import com.zds.sports.model.vo.EquipmentVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, EquipmentDO> implements EquipmentService {

    private final EquipmentMapStruct equipmentMapStruct;

    @Override
    public PageResultVO<EquipmentVO> getEquipment(int page, int size, String keyword, String category, String status) {
        Page<EquipmentDO> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<EquipmentDO> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            wrapper.and(w -> w.like(EquipmentDO::getName, keyword)
                    .or().like(EquipmentDO::getBrand, keyword)
                    .or().like(EquipmentDO::getModel, keyword));
        }
        if (StringUtils.hasText(category)) {
            wrapper.eq(EquipmentDO::getCategory, category);
        }
        if (StringUtils.hasText(status)) {
            wrapper.eq(EquipmentDO::getStatus, status);
        }
        Page<EquipmentDO> result = this.page(pageParam, wrapper);
        List<EquipmentVO> vos = result.getRecords().stream()
                .map(equipmentMapStruct::toVO)
                .collect(Collectors.toList());
        return new PageResultVO<>(result.getTotal(), result.getSize(), result.getCurrent(), result.getPages(), vos);
    }

    @Override
    public EquipmentVO createEquipment(CreateEquipmentDTO createEquipmentDTO) {
        EquipmentDO equipmentDO = equipmentMapStruct.toDO(createEquipmentDTO);
        // 转换日期字符串为 LocalDateTime
        if (StringUtils.hasText(createEquipmentDTO.getPurchaseDate())) {
            equipmentDO.setPurchaseDate(LocalDate.parse(createEquipmentDTO.getPurchaseDate(), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay());
        }
        if (StringUtils.hasText(createEquipmentDTO.getMaintenanceDate())) {
            equipmentDO.setMaintenanceDate(LocalDate.parse(createEquipmentDTO.getMaintenanceDate(), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay());
        }
        this.save(equipmentDO);
        return equipmentMapStruct.toVO(equipmentDO);
    }

    @Override
    public EquipmentVO updateEquipment(Long id, UpdateEquipmentDTO updateEquipmentDTO) {
        EquipmentDO equipmentDO = this.getById(id);
        if (equipmentDO == null) {
            throw new RuntimeException("Equipment not found");
        }
        equipmentMapStruct.updateDO(updateEquipmentDTO, equipmentDO);
        // 转换日期字符串为 LocalDateTime
        if (StringUtils.hasText(updateEquipmentDTO.getPurchaseDate())) {
            equipmentDO.setPurchaseDate(LocalDate.parse(updateEquipmentDTO.getPurchaseDate(), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay());
        }
        if (StringUtils.hasText(updateEquipmentDTO.getMaintenanceDate())) {
            equipmentDO.setMaintenanceDate(LocalDate.parse(updateEquipmentDTO.getMaintenanceDate(), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay());
        }
        this.updateById(equipmentDO);
        return equipmentMapStruct.toVO(equipmentDO);
    }

    @Override
    public void deleteEquipment(Long id) {
        this.removeById(id);
    }
}
