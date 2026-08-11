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

    public Map<String, BigDecimal> calculateDiscount (List<Order> orders){
        orders.sort(Comparator.comparing(Order::getOrderDate)); // 1. сортировали заказы по дате - чтобы все получили корректную скидку
        Map<String, BigDecimal> companyTotalPricesMap = new HashMap<>(); // 2. Создали Map(companyName, totalPrice) - чтобы хранить по каждой компании итоговую сумму для всез заказов
        for (Order order:orders){
            // у заказа проверяем есть ли эта компания в Map
            // нет - создаем элемент с ключем currentCompany, инициализируем Value нулем
            if (!companyTotalPricesMap.containsKey(order.getCompanyName())) {
                companyTotalPricesMap.put(order.getCompanyName(), BigDecimal.ZERO);
            }
            BigDecimal totalPrice = companyTotalPricesMap.get(order.getCompanyName()); // получаем текущую totalPrice компании
            BigDecimal factor = (BigDecimal.valueOf(1.0)).subtract(discount); // множитель как (1 - размер скидки)

            BigDecimal calcPriceWithDiscount = factor.multiply(BigDecimal.valueOf(order.getCementAmount())).multiply(unitPrice);

            totalPrice = totalPrice.add(calcPriceWithDiscount);
            companyTotalPricesMap.put(order.getCompanyName(), totalPrice); // обновляем totalPrice в Map
            discount = discount.subtract(stepDiscount).max(BigDecimal.ZERO); // уменьшаем скидку, но не меньше нуля
        }
        return companyTotalPricesMap;
    }
}
