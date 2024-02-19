package com.demo.jpabasics.transmgmt.crud_app.dto;

import com.demo.jpabasics.transmgmt.crud_app.entity.Order;
import com.demo.jpabasics.transmgmt.crud_app.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
