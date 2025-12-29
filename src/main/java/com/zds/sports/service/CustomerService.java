package com.zds.sports.service;

import com.zds.sports.dao.CustomerRepository;
import com.zds.sports.domain.entity.CustomerDO;
import com.zds.sports.model.dto.CreateCustomerDTO;
import com.zds.sports.model.dto.UpdateCustomerDTO;
import com.zds.sports.convert.CustomerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional(readOnly = true)
    public Page<CustomerDO> getCustomers(String keyword, Pageable pageable) {
        Specification<CustomerDO> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                String likePattern = "%" + keyword + "%";
                predicates.add(cb.or(
                        cb.like(root.get("name"), likePattern),
                        cb.like(root.get("phone"), likePattern),
                        cb.like(root.get("email"), likePattern)
                ));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return customerRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public CustomerDO getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }

    @Transactional
    public CustomerDO createCustomer(CreateCustomerDTO createCustomerDTO) {
        CustomerDO customerDO = customerMapper.toDO(createCustomerDTO);
        return customerRepository.save(customerDO);
    }

    @Transactional
    public CustomerDO updateCustomer(Long id, UpdateCustomerDTO updateCustomerDTO) {
        CustomerDO customerDO = getCustomer(id);
        customerMapper.updateDO(updateCustomerDTO, customerDO);
        return customerRepository.save(customerDO);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
