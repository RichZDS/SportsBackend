package com.zds.sports.convert;

import com.zds.sports.domain.entity.EquipmentUsageDO;
import com.zds.sports.model.dto.CreateEquipmentUsageDTO;
import com.zds.sports.model.dto.UpdateEquipmentUsageDTO;
import com.zds.sports.model.vo.EquipmentUsageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EquipmentUsageMapStruct {
    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "equipment.name", target = "equipmentName")
    EquipmentUsageVO toVO(EquipmentUsageDO usage);

    EquipmentUsageDO toDO(CreateEquipmentUsageDTO dto);

    void updateDO(UpdateEquipmentUsageDTO dto, @MappingTarget EquipmentUsageDO usage);
}

