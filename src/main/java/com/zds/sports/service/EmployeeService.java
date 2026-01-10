package com.zds.sports.service;

import com.zds.sports.convert.EmployeeMapper;
import com.zds.sports.dao.EmployeeRepository;
import com.zds.sports.domain.entity.EmployeeDO;
import com.zds.sports.model.dto.CreateEmployeeDTO;
import com.zds.sports.model.dto.UpdateEmployeeDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    public Page<EmployeeDO> getEmployees(String keyword, Pageable pageable) {
        Specification<EmployeeDO> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                String likePattern = "%" + keyword + "%";
                predicates.add(cb.or(
                        cb.like(root.get("name"), likePattern),
                        cb.like(root.get("phone"), likePattern),
                        cb.like(root.get("role"), likePattern)
                ));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return employeeRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public EmployeeDO getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
    }

    @Transactional
    public EmployeeDO createEmployee(CreateEmployeeDTO createEmployeeDTO) {
        EmployeeDO employeeDO = employeeMapper.toDO(createEmployeeDTO);
        return employeeRepository.save(employeeDO);
    }

    @Transactional
    public EmployeeDO updateEmployee(Long id, UpdateEmployeeDTO updateEmployeeDTO) {
        EmployeeDO employeeDO = getEmployee(id);
        employeeMapper.updateDO(updateEmployeeDTO, employeeDO);
        return employeeRepository.save(employeeDO);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
