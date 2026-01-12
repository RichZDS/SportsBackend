package com.zds.sports.convert;

import com.zds.sports.domain.entity.CustomerDO;
import com.zds.sports.model.dto.CreateCustomerDTO;
import com.zds.sports.model.dto.UpdateCustomerDTO;
import com.zds.sports.model.vo.CustomerVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapStruct {
    CustomerVO toVO(CustomerDO customerDO);
    CustomerDO toDO(CreateCustomerDTO createCustomerDTO);
    void updateDO(UpdateCustomerDTO updateCustomerDTO, @MappingTarget CustomerDO customerDO);
}
