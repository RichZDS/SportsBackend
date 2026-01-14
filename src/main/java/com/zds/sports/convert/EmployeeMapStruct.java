package com.zds.sports.convert;

import com.zds.sports.domain.entity.EmployeeDO;
import com.zds.sports.model.dto.CreateEmployeeDTO;
import com.zds.sports.model.dto.UpdateEmployeeDTO;
import com.zds.sports.model.vo.EmployeeVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapStruct {
    EmployeeVO toVO(EmployeeDO employeeDO);
    @org.mapstruct.Mapping(target = "hiredAt", expression = "java(parseDateTimeFlexible(createEmployeeDTO.getHiredAt()))")
    EmployeeDO toDO(CreateEmployeeDTO createEmployeeDTO);
    @org.mapstruct.Mapping(target = "hiredAt", expression = "java(parseDateTimeFlexible(updateEmployeeDTO.getHiredAt()))")
    void updateDO(UpdateEmployeeDTO updateEmployeeDTO, @MappingTarget EmployeeDO employeeDO);

    default LocalDateTime parseDateTimeFlexible(String input) {
        if (input == null || input.isEmpty()) return null;
        try {
            if (input.contains("T")) {
                OffsetDateTime odt = OffsetDateTime.parse(input);
                return odt.withOffsetSameInstant(ZoneOffset.UTC).toLocalDateTime();
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
