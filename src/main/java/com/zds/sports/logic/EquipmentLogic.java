package com.zds.sports.logic;

import com.zds.sports.convert.EquipmentMapper;
import com.zds.sports.domain.entity.EquipmentDO;
import com.zds.sports.model.dto.CreateEquipmentDTO;
import com.zds.sports.model.dto.UpdateEquipmentDTO;
import com.zds.sports.model.vo.EquipmentVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EquipmentLogic {

    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;

    public PageResultVO<EquipmentVO> getEquipment(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<EquipmentDO> equipmentPage = equipmentService.getEquipment(keyword, pageable);

        List<EquipmentVO> list = equipmentPage.getContent().stream()
                .map(equipmentMapper::toVO)
                .collect(Collectors.toList());

        return new PageResultVO<>(list, equipmentPage.getTotalElements(), page, size);
    }

    public EquipmentVO getEquipment(Long id) {
        EquipmentDO equipmentDO = equipmentService.getEquipment(id);
        return equipmentMapper.toVO(equipmentDO);
    }

    public EquipmentVO createEquipment(CreateEquipmentDTO createEquipmentDTO) {
        EquipmentDO equipmentDO = equipmentService.createEquipment(createEquipmentDTO);
        return equipmentMapper.toVO(equipmentDO);
    }

    public EquipmentVO updateEquipment(Long id, UpdateEquipmentDTO updateEquipmentDTO) {
        EquipmentDO equipmentDO = equipmentService.updateEquipment(id, updateEquipmentDTO);
        return equipmentMapper.toVO(equipmentDO);
    }

    public void deleteEquipment(Long id) {
        equipmentService.deleteEquipment(id);
    }
}
