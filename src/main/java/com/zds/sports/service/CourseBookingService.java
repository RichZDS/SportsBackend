package com.zds.sports.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.sports.convert.CourseBookingMapStruct;
import com.zds.sports.dao.CourseBookingMapper;
import com.zds.sports.domain.entity.CourseBookingDO;
import com.zds.sports.model.dto.CreateCourseBookingDTO;
import com.zds.sports.model.dto.UpdateCourseBookingDTO;
import com.zds.sports.model.vo.CourseBookingVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseBookingService extends ServiceImpl<CourseBookingMapper, CourseBookingDO> {
    private final CourseBookingMapStruct mapper;

    public CourseBookingVO create(CreateCourseBookingDTO dto) {
        CourseBookingDO booking = mapper.toDO(dto);
        booking.setStatus("BOOKED");
        booking.setBookedAt(java.time.LocalDateTime.now());
        this.save(booking);
        return mapper.toVO(booking);
    }

    public CourseBookingVO update(Long id, UpdateCourseBookingDTO dto) {
        CourseBookingDO existing = this.getById(id);
        mapper.updateDO(dto, existing);
        this.updateById(existing);
        return mapper.toVO(existing);
    }

    public void deleteById(Long id) {
        this.removeById(id);
    }
}

