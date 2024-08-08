package com.aamir.specification.service;

import com.aamir.specification.dto.PaginationDto;
import org.springframework.data.domain.Pageable;

public interface PaginationService {
    Pageable getPagination(PaginationDto paginationDTO);
}
