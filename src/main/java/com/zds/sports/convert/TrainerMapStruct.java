package com.zds.sports.convert;

import com.zds.sports.domain.entity.TrainerDO;
import com.zds.sports.model.dto.CreateTrainerDTO;
import com.zds.sports.model.dto.UpdateTrainerDTO;
import com.zds.sports.model.vo.TrainerVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TrainerMapStruct {
    TrainerVO toVO(TrainerDO trainerDO);
    TrainerDO toDO(CreateTrainerDTO createTrainerDTO);
    void updateDO(UpdateTrainerDTO updateTrainerDTO, @MappingTarget TrainerDO trainerDO);
}
