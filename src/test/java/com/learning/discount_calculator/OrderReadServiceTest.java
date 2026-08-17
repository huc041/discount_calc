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
    private final OrderReadService orderReadService = new OrderReadService();
    private final ArrayList<Order> orders = new ArrayList<Order>();


    private static final BigDecimal START = new BigDecimal("0.2");
    private static final BigDecimal STEP = new BigDecimal("0.1");
    private static final BigDecimal PRICE = new BigDecimal("10");

    private Order order(String company, int kg, String dateTime) {
        return new Order(company, kg, LocalDateTime.parse(dateTime));
    }

    @Test
    void singleOrder() {
        orders.add(order("Audi", 540, "2026-01-01T10:00:00"));
        Map<String, BigDecimal> finalMap = orderReadService.calculateDiscount(orders, START, STEP, PRICE);
        assertEquals(1, finalMap.size());
        assertEquals(0, new BigDecimal("2700.0").compareTo(finalMap.get("Audi")));
    }

    @Test
    void differentCompaniesTest(){
        orders.add(order("Audi", 540, "2026-01-01T10:00:00"));
        orders.add(order("Ford", 450, "2026-01-01T09:55:00"));
        orders.add(order("Fiat", 320, "2026-01-01T11:14:00"));
        orders.add(order("Reno", 950, "2025-12-01T18:45:00"));

        Map<String, BigDecimal> finalMap = orderReadService.calculateDiscount(orders, START, STEP, PRICE);

        assertEquals(4, finalMap.size());
        assertEquals(0, new BigDecimal("4750.0").compareTo(finalMap.get("Reno")));
        assertEquals(0, new BigDecimal("2475.0").compareTo(finalMap.get("Ford")));
        assertEquals(0, new BigDecimal("3240.0").compareTo(finalMap.get("Audi")));
        assertEquals(0, new BigDecimal("2080.0").compareTo(finalMap.get("Fiat")));
    }

    @Test
    void shouldSumTotals_whenSameCompany() {
        orders.add(order("Fiat", 320, "2026-01-01T22:15:00"));
        orders.add(order("Fiat", 960, "2027-01-01T02:45:00"));

        Map<String, BigDecimal> finalMap = orderReadService.calculateDiscount(orders, START, STEP, PRICE);
        assertEquals(0, new BigDecimal("6880.0").compareTo(finalMap.get("Fiat")));
    }

    @Test
    void shouldNotGoBelowZeroDiscount() {
        orders.add(order("Mazda", 320, "2026-10-01T22:15:00")); // 320 * 0.2 * 10
        orders.add(order("Jaguar", 450, "2027-02-01T02:45:00")); // 450 * 0.1 * 10
        orders.add(order("BMW", 505, "2027-02-01T03:45:00")); // 505* 10
        orders.add(order("Lotus", 900, "2027-04-01T02:45:00")); // 900 * 10

        Map<String, BigDecimal> finalMap = orderReadService.calculateDiscount(orders, START, STEP, PRICE);
        assertEquals(0, new BigDecimal("9000.0").compareTo(finalMap.get("Lotus")));
    }
}

