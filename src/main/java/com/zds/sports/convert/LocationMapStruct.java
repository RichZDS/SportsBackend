package com.zds.sports.convert;

import com.zds.sports.domain.entity.LocationDO;
import com.zds.sports.model.dto.CreateLocationDTO;
import com.zds.sports.model.dto.UpdateLocationDTO;
import com.zds.sports.model.vo.LocationVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LocationMapStruct {
    LocationVO toVO(LocationDO locationDO);
    LocationDO toDO(CreateLocationDTO createLocationDTO);
    void updateDO(UpdateLocationDTO updateLocationDTO, @MappingTarget LocationDO locationDO);
}

