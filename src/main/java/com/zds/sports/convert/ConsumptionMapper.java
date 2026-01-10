package com.zds.sports.convert;

import com.zds.sports.domain.entity.ConsumptionDO;
import com.zds.sports.model.dto.CreateConsumptionDTO;
import com.zds.sports.model.dto.UpdateConsumptionDTO;
import com.zds.sports.model.vo.ConsumptionVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConsumptionMapper {
    @Mapping(source = "member.name", target = "memberName")
    @Mapping(source = "member.phone", target = "memberPhone")
    ConsumptionVO toVO(ConsumptionDO consumptionDO);

    ConsumptionDO toDO(CreateConsumptionDTO createConsumptionDTO);

    void updateDO(UpdateConsumptionDTO updateConsumptionDTO, @MappingTarget ConsumptionDO consumptionDO);
}
