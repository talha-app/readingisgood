package com.readingisgood.repository;

import com.readingisgood.entity.OrderLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderLineRepository extends CrudRepository<OrderLine, Long> {
}
