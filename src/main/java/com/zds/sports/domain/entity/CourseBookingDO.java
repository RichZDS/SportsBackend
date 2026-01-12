package com.zds.sports.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("course_bookings")
public class CourseBookingDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long customerId;
    private Long courseId;
    private String status; // BOOKED/CANCELLED/COMPLETED
    private LocalDateTime bookedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private CustomerDO customer;

    @TableField(exist = false)
    private CourseDO course;
}

