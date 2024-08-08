package com.aamir.specification.request;

import lombok.Data;

@Data
public class CustomerDetailSearchOperation {
    private String searchBy;
//    private String searchOperation;
//    private String lastName;
    private Long offset;
    private Long pageNumber;
}
