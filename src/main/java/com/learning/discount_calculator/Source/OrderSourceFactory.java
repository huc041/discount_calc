package com.learning.discount_calculator.Source;

import java.nio.file.Files;
import java.nio.file.Path;

public class OrderSourceFactory {
    public static OrderSourceInterface create(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("path is NULL");
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("file does not exist");
        }
        String name = path.getFileName().toString();
        if (name.toLowerCase().endsWith(".txt")) {
            return new TxtOrderSource(path);
        }
        else if(!name.endsWith(".")) {
            return new NoExtensionOrderSource(path);
        }
        else {
            throw new IllegalArgumentException("unsupported file format: " + name);
        }
    }
}
