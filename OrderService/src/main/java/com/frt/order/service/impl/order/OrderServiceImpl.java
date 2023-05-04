package com.frt.order.service.impl.order;

import com.frt.order.exception.model.customexception.GeneralException;
import com.frt.order.exception.util.FrtError;
import com.frt.order.model.GetOrderResponse;
import com.frt.order.model.PostOrderRequest;
import com.frt.order.model.PostOrderResponse;
import com.frt.order.model.ProductItemDto;
import com.frt.order.persistence.entity.Order;
import com.frt.order.persistence.entity.ProductItem;
import com.frt.order.persistence.repository.OrderRepository;
import com.frt.order.service.OrderService;
import com.frt.order.service.util.FrtSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public GetOrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GeneralException(FrtError.NO_ORDER_WITH_GIVEN_PARAMETERS));
        return GetOrderResponse.transformEntityToResponse(order);
    }

    @Override
    public List<GetOrderResponse> getAllUserOrders(Long clientId) {
        return orderRepository.findByClientId(clientId).stream()
                .map(GetOrderResponse::transformEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PostOrderResponse createOrder(PostOrderRequest postOrderRequest) {

        // TODO check Quantity for order products
        Order order = buildOrder(postOrderRequest);

        // Save Order
        Order savedOrder = orderRepository.save(order);

        // TODO: Send Message to RabbitMQ for Product/Notification/PAD services
        return PostOrderResponse.builder()
                .message(FrtSuccess.CREATED.getDescription())
                .orderId(savedOrder.getOrderId())
                .build();
    }

    private Order buildOrder(PostOrderRequest postOrderRequest) {
        Order order = Order.builder()
                .clientId(postOrderRequest.getUserId())
                .build();

        List<ProductItem> productItemList = buildProductItem(postOrderRequest.getProductItemDtoList(), order);
        order.setProductItemList(productItemList);
        return order;
    }

    private List<ProductItem> buildProductItem(List<ProductItemDto> productItemDtoList, Order order) {
        return productItemDtoList.stream()
                .map(productItemDto -> ProductItem.transformDtoToEntity(productItemDto, order))
                .collect(Collectors.toList());
    }
}
