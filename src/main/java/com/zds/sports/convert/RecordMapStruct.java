package com.zds.sports.convert;

import com.zds.sports.domain.entity.ConsumeRecordDO;
import com.zds.sports.model.dto.CreateRecordDTO;
import com.zds.sports.model.dto.UpdateRecordDTO;
import com.zds.sports.model.vo.RecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecordMapStruct {
    @Mapping(target = "customerName", expression = "java(recordDO.getCustomer() != null ? recordDO.getCustomer().getName() : null)")
    RecordVO toVO(ConsumeRecordDO recordDO);
    
    ConsumeRecordDO toDO(CreateRecordDTO createRecordDTO);
    
    void updateDO(UpdateRecordDTO updateRecordDTO, @MappingTarget ConsumeRecordDO recordDO);
}
