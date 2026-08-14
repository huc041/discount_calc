package com.learning.discount_calculator.service;

import com.learning.discount_calculator.exceptions.WriteOrderIOException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class OrderWriteToFileService {
    public void writeOrderTotals(Map<String, BigDecimal> companyTotals, Path resultPath) throws WriteOrderIOException {

        try (BufferedWriter writer = Files.newBufferedWriter(resultPath)) {
            for (String company: companyTotals.keySet()) {
                String line = company.concat("-").concat(companyTotals.get(company).toString());
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new WriteOrderIOException("Ошибка записи в файл", e);
        }
    }
}
