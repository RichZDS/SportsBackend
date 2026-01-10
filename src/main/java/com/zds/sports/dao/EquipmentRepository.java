package com.zds.sports.dao;

import com.zds.sports.domain.entity.EquipmentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EquipmentRepository extends JpaRepository<EquipmentDO, Long>, JpaSpecificationExecutor<EquipmentDO> {
}
