package com.zds.sports.convert;

import com.zds.sports.domain.entity.MemberDO;
import com.zds.sports.model.dto.CreateMemberDTO;
import com.zds.sports.model.dto.UpdateMemberDTO;
import com.zds.sports.model.vo.MemberVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemberMapper {
    MemberVO toVO(MemberDO memberDO);
    MemberDO toDO(CreateMemberDTO createMemberDTO);
    void updateDO(UpdateMemberDTO updateMemberDTO, @MappingTarget MemberDO memberDO);
}
