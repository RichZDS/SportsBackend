package com.zds.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zds.sports.domain.entity.EquipmentDO;
import com.zds.sports.model.dto.CreateEquipmentDTO;
import com.zds.sports.model.dto.UpdateEquipmentDTO;
import com.zds.sports.model.vo.EquipmentVO;
import com.zds.sports.model.vo.PageResultVO;

public interface EquipmentService extends IService<EquipmentDO> {
    PageResultVO<EquipmentVO> getEquipment(int page, int size, String keyword, String category, String status);
    EquipmentVO createEquipment(CreateEquipmentDTO createEquipmentDTO);
    EquipmentVO updateEquipment(Long id, UpdateEquipmentDTO updateEquipmentDTO);
    void deleteEquipment(Long id);
}

