package com.zds.sports.convert;

import com.zds.sports.domain.entity.CourseBookingDO;
import com.zds.sports.model.dto.CreateCourseBookingDTO;
import com.zds.sports.model.dto.UpdateCourseBookingDTO;
import com.zds.sports.model.vo.CourseBookingVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseBookingMapStruct {
    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "course.name", target = "courseName")
    CourseBookingVO toVO(CourseBookingDO booking);

    CourseBookingDO toDO(CreateCourseBookingDTO dto);

    void updateDO(UpdateCourseBookingDTO dto, @MappingTarget CourseBookingDO booking);
}

