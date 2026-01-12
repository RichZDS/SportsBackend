package com.zds.sports.controller;

import com.zds.sports.model.dto.CreateLocationDTO;
import com.zds.sports.model.dto.UpdateLocationDTO;
import com.zds.sports.model.vo.LocationVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
@Tag(name = "Location Management", description = "APIs for managing locations")
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    @Operation(summary = "Get locations list with pagination and search")
    public PageResultVO<LocationVO> getLocations(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        return locationService.getLocations(page, size, keyword);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new location")
    public LocationVO createLocation(@Valid @RequestBody CreateLocationDTO createLocationDTO) {
        return locationService.createLocation(createLocationDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing location")
    public LocationVO updateLocation(
            @PathVariable Long id,
            @Valid @RequestBody UpdateLocationDTO updateLocationDTO) {
        return locationService.updateLocation(id, updateLocationDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a location")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }
}

