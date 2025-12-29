package com.zds.sports.dao;

import com.zds.sports.domain.entity.ConsumeRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface ConsumeRecordRepository extends JpaRepository<ConsumeRecordDO, Long>, JpaSpecificationExecutor<ConsumeRecordDO> {

    @Query("SELECT new map(c.category as category, SUM(c.amount) as value) " +
           "FROM ConsumeRecordDO c " +
           "WHERE c.paidAt BETWEEN :start AND :end " +
           "GROUP BY c.category")
    List<Map<String, Object>> sumAmountByCategory(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT new map(FUNCTION('DATE_FORMAT', c.paidAt, '%Y-%m') as month, SUM(c.amount) as value) " +
           "FROM ConsumeRecordDO c " +
           "WHERE c.paidAt BETWEEN :start AND :end " +
           "GROUP BY FUNCTION('DATE_FORMAT', c.paidAt, '%Y-%m') " +
           "ORDER BY month")
    List<Map<String, Object>> sumAmountByMonth(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
