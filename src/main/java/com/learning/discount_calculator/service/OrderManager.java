package com.learning.discount_calculator.service;

import com.learning.discount_calculator.model.Order;
import com.learning.discount_calculator.output.OrderFileService;
import com.learning.discount_calculator.source.OrderSourceFactory;
import com.learning.discount_calculator.source.OrderFileAdapter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class OrderManager {
    private final OrderService orderService;
    private final OrderFileService orderFileService;

    public OrderManager(OrderService orderService, OrderFileService orderFileService) {
        this.orderService = orderService;
        this.orderFileService= orderFileService;
    }

    public void process (Path pathInput, Path pathOutput, BigDecimal startDiscount, BigDecimal stepDiscount, BigDecimal unitPrice) throws IOException {

        OrderFileAdapter orderFileAdapter = OrderSourceFactory.create(pathInput);
        List<Order> orders = orderFileAdapter.read();

        Map<String, BigDecimal> finalMap = orderService.calculateDiscount(orders, startDiscount, stepDiscount, unitPrice);
        orderFileService.writeOrderTotals(finalMap, pathOutput);
    }
}


