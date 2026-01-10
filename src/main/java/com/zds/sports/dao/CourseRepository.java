package com.zds.sports.dao;

import com.zds.sports.domain.entity.CourseDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository extends JpaRepository<CourseDO, Long>, JpaSpecificationExecutor<CourseDO> {
}
