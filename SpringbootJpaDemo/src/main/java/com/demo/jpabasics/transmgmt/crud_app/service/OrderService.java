package com.demo.jpabasics.transmgmt.crud_app.service;

import com.demo.jpabasics.transmgmt.crud_app.dto.OrderRequest;
import com.demo.jpabasics.transmgmt.crud_app.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
