package com.zds.sports.logic;

import com.zds.sports.convert.CourseMapper;
import com.zds.sports.domain.entity.CourseDO;
import com.zds.sports.model.dto.CreateCourseDTO;
import com.zds.sports.model.dto.UpdateCourseDTO;
import com.zds.sports.model.vo.CourseVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseLogic {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public PageResultVO<CourseVO> getCourses(int page, int size, Long coachId, String status, String keyword) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<CourseDO> coursePage = courseService.getCourses(coachId, status, keyword, pageable);

        List<CourseVO> list = coursePage.getContent().stream()
                .map(courseMapper::toVO)
                .collect(Collectors.toList());

        return new PageResultVO<>(list, coursePage.getTotalElements(), page, size);
    }

    public CourseVO getCourse(Long id) {
        CourseDO courseDO = courseService.getCourse(id);
        return courseMapper.toVO(courseDO);
    }

    public CourseVO createCourse(CreateCourseDTO createCourseDTO) {
        CourseDO courseDO = courseService.createCourse(createCourseDTO);
        return courseMapper.toVO(courseDO);
    }

    public CourseVO updateCourse(Long id, UpdateCourseDTO updateCourseDTO) {
        CourseDO courseDO = courseService.updateCourse(id, updateCourseDTO);
        return courseMapper.toVO(courseDO);
    }

    public void deleteCourse(Long id) {
        courseService.deleteCourse(id);
    }
}
