package com.learning.discount_calculator.manager;

import com.learning.discount_calculator.model.Order;
import com.learning.discount_calculator.service.OrderWriteToFileService;
import com.learning.discount_calculator.service.OrderReadService;
import com.learning.discount_calculator.source.OrderSourceFactory;
import com.learning.discount_calculator.source.adapter.OrderFileAdapter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class OrderManager {
    private final OrderReadService orderReadService;
    private final OrderWriteToFileService orderFileService;

    public OrderManager(OrderReadService orderReadService, OrderWriteToFileService orderFileService) {
        this.orderReadService = orderReadService;
        this.orderFileService= orderFileService;
    }

    public void process (Path pathInput, Path pathOutput, BigDecimal startDiscount, BigDecimal stepDiscount, BigDecimal unitPrice) throws IOException {

        OrderFileAdapter orderFileAdapter = OrderSourceFactory.create(pathInput);
        List<Order> orders = orderFileAdapter.read();

        Map<String, BigDecimal> finalMap = orderReadService.calculateDiscount(orders, startDiscount, stepDiscount, unitPrice);
        orderFileService.writeOrderTotals(finalMap, pathOutput);
    }
}


