package com.aamir.specification.repo;

import com.aamir.specification.entity.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailRepo extends JpaRepository<CustomerDetail, Long>, JpaSpecificationExecutor<CustomerDetail> {
}
