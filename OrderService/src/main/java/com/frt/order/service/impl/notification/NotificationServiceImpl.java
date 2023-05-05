package com.frt.order.service.impl.notification;

import com.frt.order.model.notification.NotificationQueueDto;
import com.frt.order.model.notification.NotificationSellerDto;
import com.frt.order.model.order.PostOrderRequest;
import com.frt.order.model.order.PostOrderResponse;
import com.frt.order.model.product.ProductItemDto;
import com.frt.order.model.user.FrtUserDto;
import com.frt.order.service.FrtUserDetailsService;
import com.frt.order.service.NotificationService;
import com.frt.order.service.impl.rabbitmq.NotificationMessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final FrtUserDetailsService userDetailsService;
    private final NotificationMessageSender notificationMessageSender;

    @Override
    public void sendNotificationMessage(PostOrderRequest postOrderRequest, PostOrderResponse postOrderResponse) {

        log.info("Sending message to NotificationService");
        NotificationQueueDto notificationQueueDto = buildNotificationQueueDto(postOrderRequest,
                postOrderResponse.getTotalPrice(),
                postOrderResponse.getOrderId());
        notificationMessageSender.send(notificationQueueDto);

    }

    private NotificationQueueDto buildNotificationQueueDto(PostOrderRequest postOrderRequest, BigDecimal totalPrice, Long orderId) {

        FrtUserDto clientUser = userDetailsService.getFrtUserDto(postOrderRequest.getUserId());

        List<Long> sellerUserIds = postOrderRequest.getProductItemDtoList().stream()
                .map(ProductItemDto::getSellerId).toList();

        List<FrtUserDto> sellerUserList = userDetailsService.getFrtUserDtoList(sellerUserIds);

        // create a map where key is the seller id and value is the corresponding email
        Map<Long, String> sellerEmailMap = sellerUserList.stream()
                .collect(Collectors.toMap(FrtUserDto::getUserId, FrtUserDto::getEmail));

        // create a map where key is the seller id and value is the list of product names
        Map<Long, List<String>> sellerProductMap = postOrderRequest.getProductItemDtoList().stream()
                .collect(Collectors.groupingBy(ProductItemDto::getSellerId,
                        Collectors.mapping(ProductItemDto::getProductName, Collectors.toList())));

        // create the final list of notification seller dto
        List<NotificationSellerDto> notificationSellerDtoList = sellerEmailMap.entrySet().stream()
                .map(entry -> {
                    Long sellerId = entry.getKey();
                    String sellerEmail = entry.getValue();
                    List<String> productNames = sellerProductMap.getOrDefault(sellerId, Collections.emptyList());
                    return new NotificationSellerDto(sellerId, sellerEmail, productNames);
                }).toList();

        return NotificationQueueDto.builder()
                .notificationSellerDto(notificationSellerDtoList)
                .totalPrice(totalPrice)
                .orderId(orderId)
                .clientUser(clientUser)
                .build();
    }
}
