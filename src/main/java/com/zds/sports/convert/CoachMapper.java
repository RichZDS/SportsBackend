package com.zds.sports.convert;

import com.zds.sports.domain.entity.CoachDO;
import com.zds.sports.model.dto.CreateCoachDTO;
import com.zds.sports.model.dto.UpdateCoachDTO;
import com.zds.sports.model.vo.CoachVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CoachMapper {
    CoachVO toVO(CoachDO coachDO);
    CoachDO toDO(CreateCoachDTO createCoachDTO);
    void updateDO(UpdateCoachDTO updateCoachDTO, @MappingTarget CoachDO coachDO);
}
