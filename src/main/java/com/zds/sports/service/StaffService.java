package com.zds.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zds.sports.domain.entity.StaffDO;
import com.zds.sports.model.dto.CreateStaffDTO;
import com.zds.sports.model.dto.UpdateStaffDTO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.model.vo.StaffVO;

public interface StaffService extends IService<StaffDO> {
    PageResultVO<StaffVO> getStaff(int page, int size, String keyword, Long locationId);
    StaffVO createStaff(CreateStaffDTO createStaffDTO);
    StaffVO updateStaff(Long id, UpdateStaffDTO updateStaffDTO);
    void deleteStaff(Long id);
}

