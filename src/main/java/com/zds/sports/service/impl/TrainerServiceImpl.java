package com.zds.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.sports.convert.TrainerMapStruct;
import com.zds.sports.dao.TrainerMapper;
import com.zds.sports.domain.entity.TrainerDO;
import com.zds.sports.model.dto.CreateTrainerDTO;
import com.zds.sports.model.dto.UpdateTrainerDTO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.model.vo.TrainerVO;
import com.zds.sports.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl extends ServiceImpl<TrainerMapper, TrainerDO> implements TrainerService {

    private final TrainerMapStruct trainerMapStruct;

    @Override
    public PageResultVO<TrainerVO> getTrainers(int page, int size, String keyword) {
        Page<TrainerDO> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<TrainerDO> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            wrapper.like(TrainerDO::getName, keyword)
                    .or().like(TrainerDO::getSpecialization, keyword);
        }
        Page<TrainerDO> result = this.page(pageParam, wrapper);
        List<TrainerVO> vos = result.getRecords().stream()
                .map(trainerMapStruct::toVO)
                .collect(Collectors.toList());
        return new PageResultVO<>(result.getTotal(), result.getSize(), result.getCurrent(), result.getPages(), vos);
    }

    @Override
    public TrainerVO createTrainer(CreateTrainerDTO createTrainerDTO) {
        TrainerDO trainerDO = trainerMapStruct.toDO(createTrainerDTO);
        this.save(trainerDO);
        return trainerMapStruct.toVO(trainerDO);
    }

    @Override
    public TrainerVO updateTrainer(Long id, UpdateTrainerDTO updateTrainerDTO) {
        TrainerDO trainerDO = this.getById(id);
        if (trainerDO == null) {
            throw new RuntimeException("Trainer not found");
        }
        trainerMapStruct.updateDO(updateTrainerDTO, trainerDO);
        this.updateById(trainerDO);
        return trainerMapStruct.toVO(trainerDO);
    }

    @Override
    public void deleteTrainer(Long id) {
        this.removeById(id);
    }
}
