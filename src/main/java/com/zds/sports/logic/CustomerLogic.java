package com.zds.sports.logic;

import com.zds.sports.domain.entity.CustomerDO;
import com.zds.sports.model.dto.CreateCustomerDTO;
import com.zds.sports.model.dto.UpdateCustomerDTO;
import com.zds.sports.model.vo.CustomerVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.CustomerService;
import com.zds.sports.convert.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerLogic {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public PageResultVO<CustomerVO> getCustomers(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<CustomerDO> customerPage = customerService.getCustomers(keyword, pageable);
        
        List<CustomerVO> list = customerPage.getContent().stream()
                .map(customerMapper::toVO)
                .collect(Collectors.toList());
        
        return new PageResultVO<>(list, customerPage.getTotalElements(), page, size);
    }

    public CustomerVO getCustomer(Long id) {
        CustomerDO customerDO = customerService.getCustomer(id);
        return customerMapper.toVO(customerDO);
    }

    public CustomerVO createCustomer(CreateCustomerDTO createCustomerDTO) {
        CustomerDO customerDO = customerService.createCustomer(createCustomerDTO);
        return customerMapper.toVO(customerDO);
    }

    public CustomerVO updateCustomer(Long id, UpdateCustomerDTO updateCustomerDTO) {
        CustomerDO customerDO = customerService.updateCustomer(id, updateCustomerDTO);
        return customerMapper.toVO(customerDO);
    }

    public void deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
    }
}
