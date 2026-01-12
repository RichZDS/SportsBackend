package com.zds.sports.convert;

import com.zds.sports.domain.entity.EmployeeDO;
import com.zds.sports.model.dto.CreateEmployeeDTO;
import com.zds.sports.model.dto.UpdateEmployeeDTO;
import com.zds.sports.model.vo.EmployeeVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapStruct {
    EmployeeVO toVO(EmployeeDO employeeDO);
    EmployeeDO toDO(CreateEmployeeDTO createEmployeeDTO);
    void updateDO(UpdateEmployeeDTO updateEmployeeDTO, @MappingTarget EmployeeDO employeeDO);
}
