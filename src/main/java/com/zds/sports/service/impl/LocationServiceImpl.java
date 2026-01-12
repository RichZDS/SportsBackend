package com.zds.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.sports.convert.LocationMapStruct;
import com.zds.sports.dao.LocationMapper;
import com.zds.sports.domain.entity.LocationDO;
import com.zds.sports.model.dto.CreateLocationDTO;
import com.zds.sports.model.dto.UpdateLocationDTO;
import com.zds.sports.model.vo.LocationVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl extends ServiceImpl<LocationMapper, LocationDO> implements LocationService {

    private final LocationMapStruct locationMapStruct;

    @Override
    public PageResultVO<LocationVO> getLocations(int page, int size, String keyword) {
        Page<LocationDO> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<LocationDO> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            wrapper.like(LocationDO::getName, keyword)
                    .or().like(LocationDO::getAddress, keyword)
                    .or().like(LocationDO::getManager, keyword);
        }
        Page<LocationDO> result = this.page(pageParam, wrapper);
        List<LocationVO> vos = result.getRecords().stream()
                .map(locationMapStruct::toVO)
                .collect(Collectors.toList());
        return new PageResultVO<>(result.getTotal(), result.getSize(), result.getCurrent(), result.getPages(), vos);
    }

    @Override
    public LocationVO createLocation(CreateLocationDTO createLocationDTO) {
        LocationDO locationDO = locationMapStruct.toDO(createLocationDTO);
        this.save(locationDO);
        return locationMapStruct.toVO(locationDO);
    }

    @Override
    public LocationVO updateLocation(Long id, UpdateLocationDTO updateLocationDTO) {
        LocationDO locationDO = this.getById(id);
        if (locationDO == null) {
            throw new RuntimeException("Location not found");
        }
        locationMapStruct.updateDO(updateLocationDTO, locationDO);
        this.updateById(locationDO);
        return locationMapStruct.toVO(locationDO);
    }

    @Override
    public void deleteLocation(Long id) {
        this.removeById(id);
    }
}

