package com.zds.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.sports.convert.StaffMapStruct;
import com.zds.sports.dao.StaffMapper;
import com.zds.sports.domain.entity.LocationDO;
import com.zds.sports.domain.entity.StaffDO;
import com.zds.sports.model.dto.CreateStaffDTO;
import com.zds.sports.model.dto.UpdateStaffDTO;
import com.zds.sports.model.vo.StaffVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.LocationService;
import com.zds.sports.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl extends ServiceImpl<StaffMapper, StaffDO> implements StaffService {

    private final StaffMapStruct staffMapStruct;
    private final LocationService locationService;

    @Override
    public PageResultVO<StaffVO> getStaff(int page, int size, String keyword, Long locationId) {
        Page<StaffDO> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<StaffDO> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            wrapper.like(StaffDO::getName, keyword)
                    .or().like(StaffDO::getPhone, keyword)
                    .or().like(StaffDO::getPosition, keyword);
        }
        if (locationId != null) {
            wrapper.eq(StaffDO::getLocationId, locationId);
        }
        Page<StaffDO> result = this.page(pageParam, wrapper);
        
        // 获取所有地点ID并查询地点信息
        List<Long> locationIds = result.getRecords().stream()
                .map(StaffDO::getLocationId)
                .filter(id -> id != null)
                .distinct()
                .collect(Collectors.toList());
        
        Map<Long, String> locationMap = locationIds.stream()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> {
                            LocationDO location = locationService.getById(id);
                            return location != null ? location.getName() : "";
                        }
                ));
        
        List<StaffVO> vos = result.getRecords().stream()
                .map(staffDO -> {
                    StaffVO vo = staffMapStruct.toVO(staffDO);
                    vo.setLocationName(locationMap.getOrDefault(staffDO.getLocationId(), ""));
                    return vo;
                })
                .collect(Collectors.toList());
        
        return new PageResultVO<>(result.getTotal(), result.getSize(), result.getCurrent(), result.getPages(), vos);
    }

    @Override
    public StaffVO createStaff(CreateStaffDTO createStaffDTO) {
        StaffDO staffDO = staffMapStruct.toDO(createStaffDTO);
        this.save(staffDO);
        StaffVO vo = staffMapStruct.toVO(staffDO);
        if (staffDO.getLocationId() != null) {
            LocationDO location = locationService.getById(staffDO.getLocationId());
            if (location != null) {
                vo.setLocationName(location.getName());
            }
        }
        return vo;
    }

    @Override
    public StaffVO updateStaff(Long id, UpdateStaffDTO updateStaffDTO) {
        StaffDO staffDO = this.getById(id);
        if (staffDO == null) {
            throw new RuntimeException("Staff not found");
        }
        staffMapStruct.updateDO(updateStaffDTO, staffDO);
        this.updateById(staffDO);
        StaffVO vo = staffMapStruct.toVO(staffDO);
        if (staffDO.getLocationId() != null) {
            LocationDO location = locationService.getById(staffDO.getLocationId());
            if (location != null) {
                vo.setLocationName(location.getName());
            }
        }
        return vo;
    }

    @Override
    public void deleteStaff(Long id) {
        this.removeById(id);
    }
}

