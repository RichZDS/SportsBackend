package com.zds.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.sports.convert.CourseMapStruct;
import com.zds.sports.dao.CourseMapper;
import com.zds.sports.domain.entity.CourseDO;
import com.zds.sports.model.dto.CreateCourseDTO;
import com.zds.sports.model.dto.UpdateCourseDTO;
import com.zds.sports.model.vo.CourseVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseDO> implements CourseService {

    private final CourseMapStruct courseMapStruct;

    @Override
    public PageResultVO<CourseVO> getCourses(int page, int size, String keyword) {
        Page<CourseDO> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<CourseDO> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            wrapper.like(CourseDO::getName, keyword)
                    .or().like(CourseDO::getDescription, keyword);
        }
        Page<CourseDO> result = this.page(pageParam, wrapper);
        List<CourseVO> vos = result.getRecords().stream()
                .map(courseMapStruct::toVO)
                .collect(Collectors.toList());
        return new PageResultVO<>(result.getTotal(), result.getSize(), result.getCurrent(), result.getPages(), vos);
    }

    @Override
    public CourseVO createCourse(CreateCourseDTO createCourseDTO) {
        CourseDO courseDO = courseMapStruct.toDO(createCourseDTO);
        this.save(courseDO);
        return courseMapStruct.toVO(courseDO);
    }

    @Override
    public CourseVO updateCourse(Long id, UpdateCourseDTO updateCourseDTO) {
        CourseDO courseDO = this.getById(id);
        if (courseDO == null) {
            throw new RuntimeException("Course not found");
        }
        courseMapStruct.updateDO(updateCourseDTO, courseDO);
        this.updateById(courseDO);
        return courseMapStruct.toVO(courseDO);
    }

    @Override
    public void deleteCourse(Long id) {
        this.removeById(id);
    }
}
