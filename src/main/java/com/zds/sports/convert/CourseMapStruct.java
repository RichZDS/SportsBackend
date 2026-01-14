package com.zds.sports.convert;

import com.zds.sports.domain.entity.CourseDO;
import com.zds.sports.model.dto.CreateCourseDTO;
import com.zds.sports.model.dto.UpdateCourseDTO;
import com.zds.sports.model.vo.CourseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapStruct {
    @Mapping(target = "trainerName", expression = "java(courseDO.getTrainer() != null ? courseDO.getTrainer().getName() : null)")
    CourseVO toVO(CourseDO courseDO);
    
    @Mapping(target = "scheduleTime", expression = "java(parseDateTimeFlexible(createCourseDTO.getScheduleTime()))")
    CourseDO toDO(CreateCourseDTO createCourseDTO);
    
    @Mapping(target = "scheduleTime", expression = "java(parseDateTimeFlexible(updateCourseDTO.getScheduleTime()))")
    void updateDO(UpdateCourseDTO updateCourseDTO, @MappingTarget CourseDO courseDO);

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
