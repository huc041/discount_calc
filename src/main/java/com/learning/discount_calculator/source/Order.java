package com.learning.discount_calculator.source;
import java.time.LocalDateTime;

public class Order {

    public Order(String companyMane, int cementAmount, LocalDateTime orderDate) {
        this.companyMane = companyMane;
        this.cementAmount = cementAmount;
        this.orderDate = orderDate;
    }

    public String getCompanyMane() {
        return companyMane;
    }

    public int getCementAmount() {
        return cementAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    private String companyMane;
    private int cementAmount;
    private LocalDateTime orderDate;
}





