package com.learning.discount_calculator.Source;

import com.learning.discount_calculator.Model.Order;

import java.io.IOException;
import java.util.List;

public interface OrderSourceInterface {
    List<Order> read() throws IOException;
}
