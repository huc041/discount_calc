package com.learning.discount_calculator;

import com.learning.discount_calculator.source.OrderSourceFactory;
import com.learning.discount_calculator.source.adapter.NoExtensionOrderFileAdapter;
import com.learning.discount_calculator.source.adapter.OrderFileAdapter;
import com.learning.discount_calculator.source.adapter.TxtOrderFileAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OrderSourceFactoryTest {
    @Test
    public void shouldReadTxtFileTest() {
        Path path = Paths.get("src/test/resources/test_orders.txt");
        OrderFileAdapter orderFileAdapter = OrderSourceFactory.create(path);

        Assertions.assertTrue(orderFileAdapter instanceof TxtOrderFileAdapter);
    }

    @Test
    public void shouldReadNoExtFileTest() {
        Path path = Paths.get("src/test/resources/test_no_extension");
        OrderFileAdapter orderFileAdapter = OrderSourceFactory.create(path);

        Assertions.assertTrue(orderFileAdapter instanceof NoExtensionOrderFileAdapter);
    }

    @Test
    public void shouldNotFoundFileTest() {

        Path path = Paths.get("src/test/resources/randomFile.txt");
        Assertions.assertThrows(IllegalArgumentException.class, () -> OrderSourceFactory.create(path));
    }
}
