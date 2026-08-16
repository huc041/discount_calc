package com.learning.discount_calculator;

import com.learning.discount_calculator.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import com.learning.discount_calculator.service.OrderReadService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderReadServiceTest {

    @Test
    void calculateDiscountTest(){
        ArrayList<Order> orders = new ArrayList<Order>();

        Order order1 = new Order("Audi", 540, LocalDateTime.parse("2026-01-01T10:00:00"));
        Order order2 = new Order("Ford", 450, LocalDateTime.parse("2026-01-01T09:00:00"));
        Order order3 = new Order("Fiat", 320, LocalDateTime.parse("2026-01-01T11:00:00"));
        Order order4 = new Order("Reno", 950, LocalDateTime.parse("2026-01-01T08:00:00"));

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        OrderReadService orderReadService = new OrderReadService();
        Map<String, BigDecimal> finalMap = orderReadService.calculateDiscount(
                orders,
                BigDecimal.valueOf(0.5),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(10.0));

        assertEquals(4, finalMap.size());

        assertEquals(0, new BigDecimal("4750.0").compareTo(finalMap.get("Reno")));
        assertEquals(0, new BigDecimal("2475.0").compareTo(finalMap.get("Ford")));
        assertEquals(0, new BigDecimal("3240.0").compareTo(finalMap.get("Audi")));
        assertEquals(0, new BigDecimal("2080.0").compareTo(finalMap.get("Fiat")));
    }
}

