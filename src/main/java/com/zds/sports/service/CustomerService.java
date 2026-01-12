package com.zds.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zds.sports.domain.entity.CustomerDO;
import com.zds.sports.model.dto.CreateCustomerDTO;
import com.zds.sports.model.dto.UpdateCustomerDTO;
import com.zds.sports.model.vo.CustomerVO;
import com.zds.sports.model.vo.PageResultVO;

public interface CustomerService extends IService<CustomerDO> {
    PageResultVO<CustomerVO> getCustomers(int page, int size, String keyword);
    CustomerVO createCustomer(CreateCustomerDTO createCustomerDTO);
    CustomerVO updateCustomer(Long id, UpdateCustomerDTO updateCustomerDTO);
    void deleteCustomer(Long id);
}
