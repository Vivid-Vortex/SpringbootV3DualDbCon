package com.demo.jpabasics.transmgmt.crud_app.repository;

import com.demo.jpabasics.transmgmt.crud_app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
