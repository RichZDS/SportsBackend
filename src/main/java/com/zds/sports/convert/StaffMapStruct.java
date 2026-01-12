package com.zds.sports.convert;

import com.zds.sports.domain.entity.StaffDO;
import com.zds.sports.model.dto.CreateStaffDTO;
import com.zds.sports.model.dto.UpdateStaffDTO;
import com.zds.sports.model.vo.StaffVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StaffMapStruct {
    StaffVO toVO(StaffDO staffDO);
    StaffDO toDO(CreateStaffDTO createStaffDTO);
    void updateDO(UpdateStaffDTO updateStaffDTO, @MappingTarget StaffDO staffDO);
}

