package com.zds.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zds.sports.domain.entity.EmployeeDO;
import com.zds.sports.model.dto.CreateEmployeeDTO;
import com.zds.sports.model.dto.UpdateEmployeeDTO;
import com.zds.sports.model.vo.EmployeeVO;
import com.zds.sports.model.vo.PageResultVO;

public interface EmployeeService extends IService<EmployeeDO> {
    PageResultVO<EmployeeVO> getEmployees(int page, int size, String keyword);
    EmployeeVO createEmployee(CreateEmployeeDTO createEmployeeDTO);
    EmployeeVO updateEmployee(Long id, UpdateEmployeeDTO updateEmployeeDTO);
    void deleteEmployee(Long id);
}


