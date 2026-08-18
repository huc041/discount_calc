package com.learning.discount_calculator;

import com.learning.discount_calculator.source.OrderSourceFactory;
import com.learning.discount_calculator.source.adapter.NoExtensionOrderFileAdapter;
import com.learning.discount_calculator.source.adapter.OrderFileAdapter;
import com.learning.discount_calculator.source.adapter.TxtOrderFileAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class OrderSourceFactoryTest {
    @Test
    public void shouldChooseCorrectTxtAdapter() {
        Path path = Paths.get("src/test/resources/test_orders.txt");
        OrderFileAdapter orderFileAdapter = OrderSourceFactory.create(path);

        Assertions.assertInstanceOf(TxtOrderFileAdapter.class, orderFileAdapter);
    }

    @Test
    public void shouldChooseCorrectNoExtAdapter() {
        Path path = Paths.get("src/test/resources/test_no_extension");
        OrderFileAdapter orderFileAdapter = OrderSourceFactory.create(path);

        Assertions.assertInstanceOf(NoExtensionOrderFileAdapter.class, orderFileAdapter);
    }

    @Test
    public void shouldNotFoundFile() {
        Path path = Paths.get("definitely-missing-xyz");
        Assertions.assertThrows(IllegalArgumentException.class, () -> OrderSourceFactory.create(path));
    }

    @Test
    public void shouldShowExceptionForNullPath() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> OrderSourceFactory.create(null));
        Assertions.assertEquals("Path is NULL", thrown.getMessage());
    }

    @Test
    public void shouldShowExceptionForUnsupportedFile() {
        Path path = Paths.get("src/test/resources/orders.bak");

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> OrderSourceFactory.create(path));
        Assertions.assertEquals("unsupported file format: " + path.getFileName().toString(), thrown.getMessage());
    }
}
