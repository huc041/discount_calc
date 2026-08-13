package com.learning.discount_calculator.service;

import com.learning.discount_calculator.model.Order;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private BigDecimal discount;
    private BigDecimal stepDiscount;
    private BigDecimal unitPrice;

    public OrderService(BigDecimal discount, BigDecimal stepDiscount, BigDecimal unitPrice) {
        this.discount = discount;
        this.stepDiscount = stepDiscount;
        this.unitPrice = unitPrice;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setStepDiscount(BigDecimal stepDiscount) {
            this.stepDiscount = stepDiscount;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Map<String, BigDecimal> calculateDiscount (List<Order> orders){

        orders.sort(Comparator.comparing(Order::getOrderDate));
        Map<String, BigDecimal> companyTotalPricesMap = new HashMap<>();
        for (Order order:orders){
            if (!companyTotalPricesMap.containsKey(order.getCompanyName())) {
                companyTotalPricesMap.put(order.getCompanyName(), BigDecimal.ZERO);
            }
            BigDecimal totalPrice = companyTotalPricesMap.get(order.getCompanyName());
            BigDecimal factor = (BigDecimal.valueOf(1.0)).subtract(discount);

            BigDecimal calcPriceWithDiscount = factor.multiply(BigDecimal.valueOf(order.getCementAmount())).multiply(unitPrice);

            totalPrice = totalPrice.add(calcPriceWithDiscount);
            companyTotalPricesMap.put(order.getCompanyName(), totalPrice);
            discount = discount.subtract(stepDiscount).max(BigDecimal.ZERO);
        }
        return companyTotalPricesMap;
    }
}
