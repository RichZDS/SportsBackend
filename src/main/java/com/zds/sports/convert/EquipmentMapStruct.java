package com.zds.sports.convert;

import com.zds.sports.domain.entity.EquipmentDO;
import com.zds.sports.model.dto.CreateEquipmentDTO;
import com.zds.sports.model.dto.UpdateEquipmentDTO;
import com.zds.sports.model.vo.EquipmentVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EquipmentMapStruct {
    EquipmentVO toVO(EquipmentDO equipmentDO);
    @org.mapstruct.Mapping(target = "purchaseDate", expression = "java(parseDate(createEquipmentDTO.getPurchaseDate()))")
    @org.mapstruct.Mapping(target = "maintenanceDate", expression = "java(parseDate(createEquipmentDTO.getMaintenanceDate()))")
    EquipmentDO toDO(CreateEquipmentDTO createEquipmentDTO);
    @org.mapstruct.Mapping(target = "purchaseDate", expression = "java(parseDate(updateEquipmentDTO.getPurchaseDate()))")
    @org.mapstruct.Mapping(target = "maintenanceDate", expression = "java(parseDate(updateEquipmentDTO.getMaintenanceDate()))")
    void updateDO(UpdateEquipmentDTO updateEquipmentDTO, @MappingTarget EquipmentDO equipmentDO);

    default LocalDateTime parseDate(String date) {
        if (date == null || date.isEmpty()) return null;
        return LocalDate.parse(date).atStartOfDay();
    }
}
