package com.zds.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zds.sports.domain.entity.CourseDO;
import com.zds.sports.model.dto.CreateCourseDTO;
import com.zds.sports.model.dto.UpdateCourseDTO;
import com.zds.sports.model.vo.CourseVO;
import com.zds.sports.model.vo.PageResultVO;

public interface CourseService extends IService<CourseDO> {
    PageResultVO<CourseVO> getCourses(int page, int size, String keyword);
    CourseVO createCourse(CreateCourseDTO createCourseDTO);
    CourseVO updateCourse(Long id, UpdateCourseDTO updateCourseDTO);
    void deleteCourse(Long id);
}


