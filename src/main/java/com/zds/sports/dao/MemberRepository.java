package com.zds.sports.dao;

import com.zds.sports.domain.entity.MemberDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository extends JpaRepository<MemberDO, Long>, JpaSpecificationExecutor<MemberDO> {
}
