package com.zds.sports.dao;

import com.zds.sports.domain.entity.CoachDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoachRepository extends JpaRepository<CoachDO, Long>, JpaSpecificationExecutor<CoachDO> {
}
