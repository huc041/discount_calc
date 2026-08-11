package com.learning.discount_calculator.Output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ResultWriter {
    // по одной строке на компанию в формате <название компании> - <суммарная стоимость>.
    public void writeDataToFile(Map<String, BigDecimal> objectsMap, Path resultPath){

        try (BufferedWriter writer = Files.newBufferedWriter(resultPath)) {
            for (String company: objectsMap.keySet()) {
                String line = company.concat("-").concat(objectsMap.get(company).toString());
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
