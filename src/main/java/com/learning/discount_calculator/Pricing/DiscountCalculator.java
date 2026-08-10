package com.learning.discount_calculator.Pricing;

import com.learning.discount_calculator.Model.Order;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountCalculator {

    private BigDecimal discount;
    private BigDecimal stepDiscount;
    private BigDecimal unitPrice;

    public DiscountCalculator (BigDecimal discount, BigDecimal stepDiscount, BigDecimal unitPrice) {
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

    Map<String, BigDecimal> calculateDiscount (List<Order> orders){
        orders.sort(Comparator.comparing(Order::getOrderDate)); // сортировали заказы по дате
        Map<String, BigDecimal> companyTotalPricesMap = new HashMap<>();
        for (Order order:orders){
            // у заказа проверяем есть ли эта компания в Map
            // нет - создаем элемент с ключем currentCompany, инициализируем Value нулем
            if (!companyTotalPricesMap.containsKey(order.getCompanyMane())) {
                companyTotalPricesMap.put(order.getCompanyMane(), new BigDecimal(0.0));
            }

            BigDecimal companyInMapTotalPrice = companyTotalPricesMap.get(order.getCompanyMane());
            companyInMapTotalPrice.add(discount.multiply(BigDecimal.valueOf(order.getCementAmount())));
            companyTotalPricesMap.put(order.getCompanyMane(), companyInMapTotalPrice);

            discount = discount.subtract(stepDiscount).max(BigDecimal.ZERO); // уменьшаем, но не меньше нуля

            // 50% - первая скидка  50 - 0*step
            // 45% - вторая скидка  50 - 1*step
            // 40% - третья скидка  50 - 2*step
            // if currentDiscount <=0 (currentDiscount =0)
        }
        return companyTotalPricesMap;
    }
}
