package com.learning.discount_calculator.source;

import java.io.IOException;
import java.util.List;

public interface OrderSourceInterface {
    List<Order> read() throws IOException;
}
