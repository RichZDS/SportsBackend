package com.zds.sports.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "members")
@EntityListeners(AuditingEntityListener.class)
public class MemberDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(length = 20)
    private String phone;

    @Column(length = 128)
    private String email;

    @Column(nullable = false, length = 32)
    private String level;

    @Column(nullable = false, length = 16)
    private String status;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
