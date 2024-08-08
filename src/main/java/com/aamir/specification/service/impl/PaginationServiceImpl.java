package com.aamir.specification.service.impl;

import com.aamir.specification.dto.PaginationDto;
import com.aamir.specification.service.PaginationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PaginationServiceImpl implements PaginationService {
    @Override
    public Pageable getPagination(PaginationDto paginationDTO) {
        return PageRequest.of(paginationDTO.pageNo(), paginationDTO.offset(), Sort.by("id").descending());
    }
}
