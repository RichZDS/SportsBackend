package com.zds.sports.convert;

import com.zds.sports.domain.entity.CourseDO;
import com.zds.sports.model.dto.CreateCourseDTO;
import com.zds.sports.model.dto.UpdateCourseDTO;
import com.zds.sports.model.vo.CourseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {
    @Mapping(source = "coach.name", target = "coachName")
    @Mapping(source = "coach.phone", target = "coachPhone")
    CourseVO toVO(CourseDO courseDO);

    CourseDO toDO(CreateCourseDTO createCourseDTO);

    void updateDO(UpdateCourseDTO updateCourseDTO, @MappingTarget CourseDO courseDO);
}
