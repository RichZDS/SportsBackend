package com.zds.sports.controller;

import com.zds.sports.logic.CustomerLogic;
import com.zds.sports.model.dto.CreateCustomerDTO;
import com.zds.sports.model.dto.UpdateCustomerDTO;
import com.zds.sports.model.vo.CustomerVO;
import com.zds.sports.model.vo.PageResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Management", description = "APIs for managing customers")
public class CustomerController {

    private final CustomerLogic customerLogic;

    @GetMapping
    @Operation(summary = "Get customers list with pagination and search")
    public PageResultVO<CustomerVO> getCustomers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        return customerLogic.getCustomers(page, size, keyword);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new customer")
    public CustomerVO createCustomer(@Valid @RequestBody CreateCustomerDTO createCustomerDTO) {
        return customerLogic.createCustomer(createCustomerDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing customer")
    public CustomerVO updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerDTO updateCustomerDTO) {
        return customerLogic.updateCustomer(id, updateCustomerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a customer")
    public void deleteCustomer(@PathVariable Long id) {
        customerLogic.deleteCustomer(id);
    }
}
