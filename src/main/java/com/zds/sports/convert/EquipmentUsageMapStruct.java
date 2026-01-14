package com.zds.sports.convert;

import com.zds.sports.domain.entity.EquipmentUsageDO;
import com.zds.sports.model.dto.CreateEquipmentUsageDTO;
import com.zds.sports.model.dto.UpdateEquipmentUsageDTO;
import com.zds.sports.model.vo.EquipmentUsageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EquipmentUsageMapStruct {
    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "equipment.name", target = "equipmentName")
    EquipmentUsageVO toVO(EquipmentUsageDO usage);

    @Mapping(target = "startTime", expression = "java(parseDateTimeFlexible(dto.getStartTime()))")
    EquipmentUsageDO toDO(CreateEquipmentUsageDTO dto);
    @Mapping(target = "endTime", expression = "java(parseDateTimeFlexible(dto.getEndTime()))")
    void updateDO(UpdateEquipmentUsageDTO dto, @MappingTarget EquipmentUsageDO usage);

    default LocalDateTime parseDateTimeFlexible(String input) {
        if (input == null || input.isEmpty()) return null;
        try {
            if (input.contains("T")) {
                java.time.OffsetDateTime odt = java.time.OffsetDateTime.parse(input);
                return odt.withOffsetSameInstant(java.time.ZoneOffset.UTC).toLocalDateTime();
            } else {
                try {
                    return new java.sql.Timestamp(java.sql.Timestamp.valueOf(input).getTime()).toLocalDateTime();
                } catch (Exception ignored) {
                }
                java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return java.time.LocalDateTime.parse(input, fmt);
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid datetime format: " + input);
        }
    }
}
