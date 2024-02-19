package com.demo.jpabasics.transmgmt.crud_app.repository;

import com.demo.jpabasics.transmgmt.crud_app.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
