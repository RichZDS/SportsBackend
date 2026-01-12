package com.zds.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.sports.convert.EmployeeMapStruct;
import com.zds.sports.dao.EmployeeMapper;
import com.zds.sports.domain.entity.EmployeeDO;
import com.zds.sports.model.dto.CreateEmployeeDTO;
import com.zds.sports.model.dto.UpdateEmployeeDTO;
import com.zds.sports.model.vo.EmployeeVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeDO> implements EmployeeService {

    private final EmployeeMapStruct employeeMapStruct;

    @Override
    public PageResultVO<EmployeeVO> getEmployees(int page, int size, String keyword) {
        Page<EmployeeDO> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<EmployeeDO> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            wrapper.like(EmployeeDO::getName, keyword)
                    .or().like(EmployeeDO::getPosition, keyword);
        }
        Page<EmployeeDO> result = this.page(pageParam, wrapper);
        List<EmployeeVO> vos = result.getRecords().stream()
                .map(employeeMapStruct::toVO)
                .collect(Collectors.toList());
        return new PageResultVO<>(result.getTotal(), result.getSize(), result.getCurrent(), result.getPages(), vos);
    }

    @Override
    public EmployeeVO createEmployee(CreateEmployeeDTO createEmployeeDTO) {
        EmployeeDO employeeDO = employeeMapStruct.toDO(createEmployeeDTO);
        this.save(employeeDO);
        return employeeMapStruct.toVO(employeeDO);
    }

    @Override
    public EmployeeVO updateEmployee(Long id, UpdateEmployeeDTO updateEmployeeDTO) {
        EmployeeDO employeeDO = this.getById(id);
        if (employeeDO == null) {
            throw new RuntimeException("Employee not found");
        }
        employeeMapStruct.updateDO(updateEmployeeDTO, employeeDO);
        this.updateById(employeeDO);
        return employeeMapStruct.toVO(employeeDO);
    }

    @Override
    public void deleteEmployee(Long id) {
        this.removeById(id);
    }
}
