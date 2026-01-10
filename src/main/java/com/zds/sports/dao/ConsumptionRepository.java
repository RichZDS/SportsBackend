package com.zds.sports.dao;

import com.zds.sports.domain.entity.ConsumptionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsumptionRepository extends JpaRepository<ConsumptionDO, Long>, JpaSpecificationExecutor<ConsumptionDO> {
}
