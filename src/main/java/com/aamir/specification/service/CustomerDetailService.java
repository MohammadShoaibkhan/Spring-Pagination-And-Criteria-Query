package com.aamir.specification.service;

import com.aamir.specification.dto.PaginationDto;
import com.aamir.specification.request.CustomerDetailReqDTO;
import com.aamir.specification.request.CustomerDetailSearchOperation;
import com.aamir.specification.response.CustomerDetailResDTO;

import java.util.Map;

public interface CustomerDetailService {
    CustomerDetailResDTO saveCustomerDetail(CustomerDetailReqDTO customerDetailReqDTO);

    Map<String, Object> getAllCustomerDetail(PaginationDto paginationDto);

    Map<String, Object> getCustomerDetailBySearchCriteria(CustomerDetailSearchOperation customerDetailSearchOperation);
}
