package com.aamir.specification.service.impl;

import com.aamir.specification.dto.PaginationDto;
import com.aamir.specification.entity.CustomerDetail;
import com.aamir.specification.repo.CustomerDetailRepo;
import com.aamir.specification.request.CustomerDetailReqDTO;
import com.aamir.specification.request.CustomerDetailSearchOperation;
import com.aamir.specification.response.CustomerDetailResDTO;
import com.aamir.specification.service.CustomerDetailService;
import com.aamir.specification.service.PaginationService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final ModelMapper modelMapper;
    private final CustomerDetailRepo customerDetailRepo;
    private final PaginationService paginationService;

    @Override
    public CustomerDetailResDTO saveCustomerDetail(CustomerDetailReqDTO customerDetailReqDTO) {
        log.info("inside CustomerDetailServiceImpl, saveCustomerDetail method called!!");
        CustomerDetail customerDetail = modelMapper.map(customerDetailReqDTO, CustomerDetail.class);
        CustomerDetail detail = customerDetailRepo.save(customerDetail);
        return modelMapper.map(detail, CustomerDetailResDTO.class);
    }

    @Override
    public Map<String, Object> getAllCustomerDetail(PaginationDto paginationDto) {
        log.info("inside CustomerDetailServiceImpl, getAllCustomerDetail method called!!");
        Page<CustomerDetail> customerDetails = customerDetailRepo.findAll(paginationService.getPagination(paginationDto));
        List<CustomerDetail> filterCustomerDetails = customerDetails.stream().toList();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("data", filterCustomerDetails);
        dataMap.put("totalPage", customerDetails.getTotalPages());
        dataMap.put("currentPage", customerDetails.getNumber());
        dataMap.put("totalRecords", filterCustomerDetails.size());
        return dataMap;
    }

    @Override
    public Map<String, Object> getCustomerDetailBySearchCriteria(CustomerDetailSearchOperation customerDetailSearchOperation) {
        Page<CustomerDetail> customerDetails = customerDetailRepo.findAll(getQuerySpecification(customerDetailSearchOperation),
                paginationService.getPagination(new PaginationDto(customerDetailSearchOperation.getOffset().intValue()
                        , customerDetailSearchOperation.getPageNumber().intValue())));

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("data", customerDetails);
        mapData.put("totalPage", customerDetails.getTotalPages());
        mapData.put("currentPage", customerDetails.getNumber());
        mapData.put("totalRecords", customerDetails.getTotalElements());
        return mapData;
    }

    // Advance filter search by Pagination
    private Specification<CustomerDetail> getQuerySpecification(CustomerDetailSearchOperation customerDetailSearchOperation) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (!StringUtils.hasLength(customerDetailSearchOperation.getSearchBy())) {
                throw new RuntimeException("Search input key is mandatory");
            }
            predicate.add(criteriaBuilder.like(root.get("firstName"), "%" + customerDetailSearchOperation.getSearchBy() + "%"));
            predicate.add(criteriaBuilder.like(root.get("lastName"), "%" + customerDetailSearchOperation.getSearchBy() + "%"));
            return criteriaBuilder.or(predicate.toArray(new jakarta.persistence.criteria.Predicate[predicate.size()]));
        };
    }
}
