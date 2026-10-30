package com.example.service;

import com.example.dto.request.OrderCreateRequestDTO;
import com.example.dto.response.OrderResponseDTO;
import com.example.entity.ComputerEntity;
import com.example.entity.OrderEntity;
import com.example.entity.OrderItemEntity;
import com.example.exception.NotFoundException;
import com.example.repository.ComputerRepository;
import com.example.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ComputerRepository computerRepository;

    @Transactional
    public OrderResponseDTO createOrder(OrderCreateRequestDTO request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        OrderEntity order = new OrderEntity();
        order.setUsername(username);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItemEntity> orderItems = new ArrayList<>();
        double total = 0.0;

        for (var itemReq : request.getItems()) {
            ComputerEntity computer = computerRepository.findById(itemReq.getComputerId())
                    .orElseThrow(() -> new NotFoundException("Kompüter tapılmadı ID: " + itemReq.getComputerId()));


            if (computer.getUsername() != null && computer.getUsername().equalsIgnoreCase(username)) {
                throw new RuntimeException("Siz öz əlavə etdiyiniz kompüteri (" + computer.getBrand() + " " + computer.getModel() + ") ala bilməzsiniz!");
            }

            int requestedQty = itemReq.getQuantity() != null ? itemReq.getQuantity() : 1;


            if (computer.getQuantity() == null || computer.getQuantity() < requestedQty) {
                throw new RuntimeException("Kifayət qədər stok yoxdur! " + computer.getBrand() + " " + computer.getModel() + " üçün mövcud stok: " + (computer.getQuantity() != null ? computer.getQuantity() : 0));
            }


            int newQuantity = computer.getQuantity() - requestedQty;
            if (newQuantity <= 0) {

                computerRepository.delete(computer);
            } else {

                computer.setQuantity(newQuantity);
                computerRepository.save(computer);
            }

            double itemTotal = computer.getPrice() * requestedQty;
            total += itemTotal;

            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .computerId(computer.getId())
                    .computerName(computer.getBrand() + " " + computer.getModel())
                    .price(computer.getPrice())
                    .quantity(requestedQty)
                    .order(order)
                    .build();

            orderItems.add(orderItem);
        }

        order.setTotalPrice(total);
        order.setItems(orderItems);

        OrderEntity savedOrder = orderRepository.save(order);
        return mapToDTO(savedOrder);
    }

    private OrderResponseDTO mapToDTO(OrderEntity entity) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setOrderDate(entity.getOrderDate());

        List<OrderResponseDTO.OrderItemResponseDTO> itemDTOs = entity.getItems().stream().map(i -> {
            OrderResponseDTO.OrderItemResponseDTO itemDTO = new OrderResponseDTO.OrderItemResponseDTO();
            itemDTO.setComputerId(i.getComputerId());
            itemDTO.setComputerName(i.getComputerName());
            itemDTO.setPrice(i.getPrice());
            itemDTO.setQuantity(i.getQuantity());
            return itemDTO;
        }).toList();

        dto.setItems(itemDTOs);
        return dto;
    }
}