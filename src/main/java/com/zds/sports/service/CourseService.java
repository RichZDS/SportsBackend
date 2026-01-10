package com.zds.sports.service;

import com.zds.sports.convert.CourseMapper;
import com.zds.sports.dao.CourseRepository;
import com.zds.sports.domain.entity.CourseDO;
import com.zds.sports.model.dto.CreateCourseDTO;
import com.zds.sports.model.dto.UpdateCourseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Transactional(readOnly = true)
    public Page<CourseDO> getCourses(Long coachId, String status, String keyword, Pageable pageable) {
        Specification<CourseDO> spec = (root, query, cb) -> {
            if (!Long.class.equals(query.getResultType())) {
                root.fetch("coach", JoinType.LEFT);
                query.distinct(true);
            }
            List<Predicate> predicates = new ArrayList<>();
            if (coachId != null) {
                predicates.add(cb.equal(root.get("coachId"), coachId));
            }
            if (StringUtils.hasText(status)) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (StringUtils.hasText(keyword)) {
                String likePattern = "%" + keyword + "%";
                predicates.add(cb.like(root.get("name"), likePattern));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return courseRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public CourseDO getCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }

    @Transactional
    public CourseDO createCourse(CreateCourseDTO createCourseDTO) {
        CourseDO courseDO = courseMapper.toDO(createCourseDTO);
        return courseRepository.save(courseDO);
    }

    @Transactional
    public CourseDO updateCourse(Long id, UpdateCourseDTO updateCourseDTO) {
        CourseDO courseDO = getCourse(id);
        courseMapper.updateDO(updateCourseDTO, courseDO);
        return courseRepository.save(courseDO);
    }

    @Transactional
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }
}
