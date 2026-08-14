package com.learning.discount_calculator.source.adapter;

import com.learning.discount_calculator.model.Order;
import com.learning.discount_calculator.source.OrderFileAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

abstract class AbstractDelimitedOrderSource implements OrderFileAdapter {
    private final Path path;
    abstract protected String delimiter();
    abstract protected String datePattern();
    public AbstractDelimitedOrderSource(Path path) {
        this.path = path;
    }

    public List<Order> read() throws IOException {
    List<String> lines = Files.readAllLines(path);
    String sep = delimiter();
    ArrayList<Order> orders = new ArrayList<Order>();
        if (!lines.isEmpty()) {
            for (String s : lines) {
                if (s.contains(sep)) {
                    List<String> parts = List.of(s.split(Pattern.quote(sep)));
                    if (!parts.isEmpty() && parts.size() >= 2) {

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern());
                        LocalDateTime time = LocalDateTime.parse((String) parts.get(0), formatter);

                        String company = parts.get(1);
                        int cementAm = Integer.parseInt(parts.get(2));

                        if (!company.isEmpty() && cementAm > 0 && time != null) {
                            Order newOrder = new Order(company, cementAm, time);
                            orders.add(newOrder);
                        }

                    }
                }
            }
        }
        return orders;
    }
}
