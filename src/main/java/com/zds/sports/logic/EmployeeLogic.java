package com.zds.sports.logic;

import com.zds.sports.convert.EmployeeMapper;
import com.zds.sports.domain.entity.EmployeeDO;
import com.zds.sports.model.dto.CreateEmployeeDTO;
import com.zds.sports.model.dto.UpdateEmployeeDTO;
import com.zds.sports.model.vo.EmployeeVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeLogic {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public PageResultVO<EmployeeVO> getEmployees(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<EmployeeDO> employeePage = employeeService.getEmployees(keyword, pageable);

        List<EmployeeVO> list = employeePage.getContent().stream()
                .map(employeeMapper::toVO)
                .collect(Collectors.toList());

        return new PageResultVO<>(list, employeePage.getTotalElements(), page, size);
    }

    public EmployeeVO getEmployee(Long id) {
        EmployeeDO employeeDO = employeeService.getEmployee(id);
        return employeeMapper.toVO(employeeDO);
    }

    public EmployeeVO createEmployee(CreateEmployeeDTO createEmployeeDTO) {
        EmployeeDO employeeDO = employeeService.createEmployee(createEmployeeDTO);
        return employeeMapper.toVO(employeeDO);
    }

    public EmployeeVO updateEmployee(Long id, UpdateEmployeeDTO updateEmployeeDTO) {
        EmployeeDO employeeDO = employeeService.updateEmployee(id, updateEmployeeDTO);
        return employeeMapper.toVO(employeeDO);
    }

    public void deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
    }
}
