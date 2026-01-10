package com.zds.sports.logic;

import com.zds.sports.convert.ConsumptionMapper;
import com.zds.sports.domain.entity.ConsumptionDO;
import com.zds.sports.model.dto.CreateConsumptionDTO;
import com.zds.sports.model.dto.UpdateConsumptionDTO;
import com.zds.sports.model.vo.ConsumptionVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConsumptionLogic {

    private final ConsumptionService consumptionService;
    private final ConsumptionMapper consumptionMapper;

    public PageResultVO<ConsumptionVO> getConsumptions(int page, int size, Long memberId, String category, LocalDateTime start, LocalDateTime end) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "paidAt"));
        Page<ConsumptionDO> consumptionPage = consumptionService.getConsumptions(memberId, category, start, end, pageable);

        List<ConsumptionVO> list = consumptionPage.getContent().stream()
                .map(consumptionMapper::toVO)
                .collect(Collectors.toList());

        return new PageResultVO<>(list, consumptionPage.getTotalElements(), page, size);
    }

    public ConsumptionVO getConsumption(Long id) {
        ConsumptionDO consumptionDO = consumptionService.getConsumption(id);
        return consumptionMapper.toVO(consumptionDO);
    }

    public ConsumptionVO createConsumption(CreateConsumptionDTO createConsumptionDTO) {
        ConsumptionDO consumptionDO = consumptionService.createConsumption(createConsumptionDTO);
        return consumptionMapper.toVO(consumptionDO);
    }

    public ConsumptionVO updateConsumption(Long id, UpdateConsumptionDTO updateConsumptionDTO) {
        ConsumptionDO consumptionDO = consumptionService.updateConsumption(id, updateConsumptionDTO);
        return consumptionMapper.toVO(consumptionDO);
    }

    public void deleteConsumption(Long id) {
        consumptionService.deleteConsumption(id);
    }
}
