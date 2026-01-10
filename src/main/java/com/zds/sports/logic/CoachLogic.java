package com.zds.sports.logic;

import com.zds.sports.convert.CoachMapper;
import com.zds.sports.domain.entity.CoachDO;
import com.zds.sports.model.dto.CreateCoachDTO;
import com.zds.sports.model.dto.UpdateCoachDTO;
import com.zds.sports.model.vo.CoachVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.CoachService;
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
public class CoachLogic {

    private final CoachService coachService;
    private final CoachMapper coachMapper;

    public PageResultVO<CoachVO> getCoaches(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<CoachDO> coachPage = coachService.getCoaches(keyword, pageable);

        List<CoachVO> list = coachPage.getContent().stream()
                .map(coachMapper::toVO)
                .collect(Collectors.toList());

        return new PageResultVO<>(list, coachPage.getTotalElements(), page, size);
    }

    public CoachVO getCoach(Long id) {
        CoachDO coachDO = coachService.getCoach(id);
        return coachMapper.toVO(coachDO);
    }

    public CoachVO createCoach(CreateCoachDTO createCoachDTO) {
        CoachDO coachDO = coachService.createCoach(createCoachDTO);
        return coachMapper.toVO(coachDO);
    }

    public CoachVO updateCoach(Long id, UpdateCoachDTO updateCoachDTO) {
        CoachDO coachDO = coachService.updateCoach(id, updateCoachDTO);
        return coachMapper.toVO(coachDO);
    }

    public void deleteCoach(Long id) {
        coachService.deleteCoach(id);
    }
}
