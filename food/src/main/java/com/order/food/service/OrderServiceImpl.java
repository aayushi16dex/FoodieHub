package com.order.food.service;

import com.order.food.dto.OrderItemRequestDto;
import com.order.food.dto.OrderItemResponseDto;
import com.order.food.dto.OrderRequestDto;
import com.order.food.dto.OrderResponseDto;
import com.order.food.enums.OrderStatus;
import com.order.food.exception.RecordNotFoundException;
import com.order.food.model.Customer;
import com.order.food.model.FoodItem;
import com.order.food.model.Order;
import com.order.food.model.OrderItem;
import com.order.food.repository.CustomerRepository;
import com.order.food.repository.FoodItemRepository;
import com.order.food.repository.OrderItemRepository;
import com.order.food.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        Customer customer = validateCustomer(dto.getCustomerId());
        Order order = initializeOrder(customer);

        List<OrderItemResponseDto> itemResponses = new ArrayList<>();
        double totalAmount = processOrderItems(dto.getItems(), order, itemResponses);

        order.setTotalAmount(totalAmount);
        orderRepository.save(order);

        return buildOrderResponse(order, itemResponses, customer);
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }


    private Customer validateCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RecordNotFoundException("Customer not found with id " + customerId));
    }

    private Order initializeOrder(Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PAYMENT_PENDING);
        order.setItems(new ArrayList<>());
        return orderRepository.save(order);
    }

    private double processOrderItems(List<OrderItemRequestDto> itemsDto, Order order, List<OrderItemResponseDto> itemResponses) {
        double total = 0.0;
        for (OrderItemRequestDto itemDto : itemsDto)  {
            FoodItem foodItem = foodItemRepository.findById(itemDto.getFoodItemId())
                    .orElseThrow(() -> new RecordNotFoundException("Food item not found with id: " + itemDto.getFoodItemId()));

            int qty = itemDto.getQuantity();
            double unitPrice = foodItem.getPrice();
            double totalPrice = qty * unitPrice;
            total += totalPrice;

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setFoodItem(foodItem);
            orderItem.setQuantity(qty);
            orderItem.setPrice(totalPrice);
            orderItemRepository.save(orderItem);

            OrderItemResponseDto responseDto = new OrderItemResponseDto();
            responseDto.setFoodName(foodItem.getFoodName());
            responseDto.setQuantity(qty);
            responseDto.setPricePerUnit(unitPrice);
            responseDto.setTotalPrice(totalPrice);
            itemResponses.add(responseDto);
        }
        return total;
    }

    private OrderResponseDto buildOrderResponse(Order order, List<OrderItemResponseDto> items, Customer customer) {
        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(order.getId());
        response.setCustomerName(customer.getCustomerName());
        response.setOrderDate(order.getOrderDate());
        response.setOrderStatus(order.getStatus());
        response.setOrderItems(items);
        response.setTotalAmount(order.getTotalAmount());
        return response;
    }
}