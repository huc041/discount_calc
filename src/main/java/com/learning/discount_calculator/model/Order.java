package com.learning.discount_calculator.model;
import java.time.LocalDateTime;

public class Order {

    public Order(String companyName, int cementAmount, LocalDateTime orderDate) {
        this.companyName = companyName;
        this.cementAmount = cementAmount;
        this.orderDate = orderDate;
    }

    public String getCompanyName() {
        return companyName;
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

    private String companyName;
    private int cementAmount;
    private LocalDateTime orderDate;
}





