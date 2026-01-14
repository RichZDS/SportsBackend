package com.zds.sports.convert;

import com.zds.sports.domain.entity.ConsumeRecordDO;
import com.zds.sports.model.dto.CreateRecordDTO;
import com.zds.sports.model.dto.UpdateRecordDTO;
import com.zds.sports.model.vo.RecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecordMapStruct {
    @Mapping(target = "customerName", expression = "java(recordDO.getCustomer() != null ? recordDO.getCustomer().getName() : null)")
    RecordVO toVO(ConsumeRecordDO recordDO);
    
    @Mapping(target = "paidAt", expression = "java(parseDateTimeFlexible(createRecordDTO.getPaidAt()))")
    ConsumeRecordDO toDO(CreateRecordDTO createRecordDTO);
    
    @Mapping(target = "paidAt", expression = "java(parseDateTimeFlexible(updateRecordDTO.getPaidAt()))")
    void updateDO(UpdateRecordDTO updateRecordDTO, @MappingTarget ConsumeRecordDO recordDO);

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
