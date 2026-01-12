package com.zds.sports.convert;

import com.zds.sports.domain.entity.EquipmentDO;
import com.zds.sports.model.dto.CreateEquipmentDTO;
import com.zds.sports.model.dto.UpdateEquipmentDTO;
import com.zds.sports.model.vo.EquipmentVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EquipmentMapStruct {
    EquipmentVO toVO(EquipmentDO equipmentDO);
    EquipmentDO toDO(CreateEquipmentDTO createEquipmentDTO);
    void updateDO(UpdateEquipmentDTO updateEquipmentDTO, @MappingTarget EquipmentDO equipmentDO);
}
