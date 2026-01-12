package com.zds.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zds.sports.convert.CustomerMapStruct;
import com.zds.sports.dao.CustomerMapper;
import com.zds.sports.domain.entity.CustomerDO;
import com.zds.sports.model.dto.CreateCustomerDTO;
import com.zds.sports.model.dto.UpdateCustomerDTO;
import com.zds.sports.model.vo.CustomerVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, CustomerDO> implements CustomerService {

    private final CustomerMapStruct customerMapStruct;

    @Override
    public PageResultVO<CustomerVO> getCustomers(int page, int size, String keyword) {
        Page<CustomerDO> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<CustomerDO> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            wrapper.like(CustomerDO::getName, keyword)
                    .or().like(CustomerDO::getPhone, keyword)
                    .or().like(CustomerDO::getEmail, keyword);
        }
        Page<CustomerDO> result = this.page(pageParam, wrapper);
        List<CustomerVO> vos = result.getRecords().stream()
                .map(customerMapStruct::toVO)
                .collect(Collectors.toList());
        return new PageResultVO<>(result.getTotal(), result.getSize(), result.getCurrent(), result.getPages(), vos);
    }

    @Override
    public CustomerVO createCustomer(CreateCustomerDTO createCustomerDTO) {
        CustomerDO customerDO = customerMapStruct.toDO(createCustomerDTO);
        this.save(customerDO);
        return customerMapStruct.toVO(customerDO);
    }

    @Override
    public CustomerVO updateCustomer(Long id, UpdateCustomerDTO updateCustomerDTO) {
        CustomerDO customerDO = this.getById(id);
        if (customerDO == null) {
            throw new RuntimeException("Customer not found");
        }
        customerMapStruct.updateDO(updateCustomerDTO, customerDO);
        this.updateById(customerDO);
        return customerMapStruct.toVO(customerDO);
    }

    @Override
    public void deleteCustomer(Long id) {
        this.removeById(id);
    }
}
