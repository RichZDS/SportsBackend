package com.zds.sports.dao;

import com.zds.sports.domain.entity.EmployeeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepository extends JpaRepository<EmployeeDO, Long>, JpaSpecificationExecutor<EmployeeDO> {
}
