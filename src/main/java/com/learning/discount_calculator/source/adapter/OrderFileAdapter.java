package com.learning.discount_calculator.source.adapter;

import com.learning.discount_calculator.model.Order;

import java.io.IOException;
import java.util.List;

public interface OrderFileAdapter {
    List<Order> read() throws IOException;
}
