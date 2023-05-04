package com.frt.order.service.impl.order;

import com.frt.order.model.OrderResponse;
import com.frt.order.persistence.repository.OrderRepository;
import com.frt.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderResponse> getAllUserOrders(Long userId) {
        return null;
    }
}
