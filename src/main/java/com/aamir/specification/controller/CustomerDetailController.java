package com.aamir.specification.controller;

import com.aamir.specification.dto.PaginationDto;
import com.aamir.specification.repo.CustomerDetailRepo;
import com.aamir.specification.request.CustomerDetailReqDTO;
import com.aamir.specification.request.CustomerDetailSearchOperation;
import com.aamir.specification.response.CustomerDetailResDTO;
import com.aamir.specification.service.CustomerDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/customer/")
@RequiredArgsConstructor
public class CustomerDetailController {

    private final CustomerDetailService customerDetailService;
    private final CustomerDetailRepo customerDetailRepository;

    @PostMapping(value = "/save")
    public CustomerDetailResDTO saveCustomerDetail(@Valid @RequestBody CustomerDetailReqDTO customerDetailReqDTO) {
        log.info("inside CustomerDetailController, saveCustomerDetail method called !!");
        return customerDetailService.saveCustomerDetail(customerDetailReqDTO);
    }

    @GetMapping(value = "/getAllCustomerDetail/{page}/{offset}")
    public Map<String, Object> getAllCustomerDetail(@PathVariable Long page, @PathVariable Long offset) {
        log.info("inside CustomerDetailController, getAllCustomerDetail method called !!");
        return customerDetailService.getAllCustomerDetail(new PaginationDto(offset.intValue(),page.intValue()));
    }

    @PostMapping(value = "/customerDetailSearchCriteria")
    public Map<String, Object> getCustomerDetailBySearchCriteria(
            @RequestBody CustomerDetailSearchOperation customerDetailSearchOperation) {
        log.info("inside CustomerDetailController, getCustomerDetailBySearchCriteria method called !!");
        return customerDetailService.getCustomerDetailBySearchCriteria(customerDetailSearchOperation);
    }
}
