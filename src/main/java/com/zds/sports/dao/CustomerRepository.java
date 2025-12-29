package com.zds.sports.dao;

import com.zds.sports.domain.entity.CustomerDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDO, Long>, JpaSpecificationExecutor<CustomerDO> {
}
