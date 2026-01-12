package com.zds.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zds.sports.domain.entity.TrainerDO;
import com.zds.sports.model.dto.CreateTrainerDTO;
import com.zds.sports.model.dto.UpdateTrainerDTO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.model.vo.TrainerVO;

public interface TrainerService extends IService<TrainerDO> {
    PageResultVO<TrainerVO> getTrainers(int page, int size, String keyword);
    TrainerVO createTrainer(CreateTrainerDTO createTrainerDTO);
    TrainerVO updateTrainer(Long id, UpdateTrainerDTO updateTrainerDTO);
    void deleteTrainer(Long id);
}


