package com.demo.jpabasics.transmgmt.crud_app.service.impl;

import com.demo.jpabasics.transmgmt.crud_app.dto.OrderRequest;
import com.demo.jpabasics.transmgmt.crud_app.dto.OrderResponse;
import com.demo.jpabasics.transmgmt.crud_app.entity.Order;
import com.demo.jpabasics.transmgmt.crud_app.entity.Payment;
import com.demo.jpabasics.transmgmt.crud_app.exception.PaymentException;
import com.demo.jpabasics.transmgmt.crud_app.repository.OrderRepository;
import com.demo.jpabasics.transmgmt.crud_app.repository.PaymentRepository;
import com.demo.jpabasics.transmgmt.crud_app.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    /**
     * We must keep the fields in the class as final, especially if we are using constructor injection.
     * The reason is that it will make the class and fields immutable from the beginning only after the object
     * creation through the constructor injection.
     * This is reason we should alway prefer the constructor injection over setter injection, since in setter
     * injection, the fields (which are and should always be final unless required specifically) assignments will happen after the object creation through setter injection. So there is
     * always a gap left between object creation and setter injection.
     */
    final private OrderRepository orderRepository;
    final private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type do not support");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTackingNumber(order.getOrderTackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;
    }
}
