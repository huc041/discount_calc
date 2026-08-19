package com.learning.discount_calculator;

import com.learning.discount_calculator.model.Order;
import com.learning.discount_calculator.source.adapter.TxtOrderFileAdapter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TxtOrderFileAdapterTest {
    private static void assertOrder(Order actual, String company, int kg, LocalDateTime date){
        assertEquals(company, actual.getCompanyName());
        assertEquals(kg, actual.getCementAmount());
        assertEquals(date, actual.getOrderDate());
    }

    private static List<Order> readTxt(String relativePath) throws IOException {
        return new TxtOrderFileAdapter(Paths.get(relativePath)).read();
    }

    @Test
    public void shouldReadTxtFile() throws IOException {
        Path path = Paths.get("src/test/resources/test_orders_for_txt_adapter.txt");

        List<Order> read = readTxt(path.toString());
        assertEquals(2, read.size());

        Order order1 = read.get(0);
        assertOrder(order1,"Lego",250, LocalDateTime.parse("2026-01-15T10:00:00"));

        Order order2 = read.get(1);
        assertOrder(order2,"Minecraft",425, LocalDateTime.parse("2025-12-15T20:00:00"));
    }

    @Test
    public void shouldReadTxtFileWithInvalidLine() throws IOException {
        Path path = Paths.get("src/test/resources/one_valid_one_invalid_txt_file.txt");

        List<Order> read = readTxt(path.toString());
        assertEquals(1, read.size());

        Order order = read.get(0);
        assertOrder(order,"Dendy",444, LocalDateTime.parse("2026-12-15T13:13:13"));
    }

}
