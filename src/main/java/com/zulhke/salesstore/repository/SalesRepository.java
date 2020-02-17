package com.zulhke.salesstore.repository;

import com.zulhke.salesstore.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
}
