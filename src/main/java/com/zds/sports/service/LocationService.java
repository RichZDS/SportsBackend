package com.zds.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zds.sports.domain.entity.LocationDO;
import com.zds.sports.model.dto.CreateLocationDTO;
import com.zds.sports.model.dto.UpdateLocationDTO;
import com.zds.sports.model.vo.LocationVO;
import com.zds.sports.model.vo.PageResultVO;

public interface LocationService extends IService<LocationDO> {
    PageResultVO<LocationVO> getLocations(int page, int size, String keyword);
    LocationVO createLocation(CreateLocationDTO createLocationDTO);
    LocationVO updateLocation(Long id, UpdateLocationDTO updateLocationDTO);
    void deleteLocation(Long id);
}

