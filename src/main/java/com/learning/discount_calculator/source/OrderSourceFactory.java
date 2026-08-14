package com.learning.discount_calculator.source;

import com.learning.discount_calculator.source.adapter.NoExtensionOrderFileAdapter;
import com.learning.discount_calculator.source.adapter.OrderFileAdapter;
import com.learning.discount_calculator.source.adapter.TxtOrderFileAdapter;

import java.nio.file.Files;
import java.nio.file.Path;

public class OrderSourceFactory {
    public static OrderFileAdapter create(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("path is NULL");
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("file does not exist");
        }
        String name = path.getFileName().toString();
        if (name.toLowerCase().endsWith(".txt")) {
            return new TxtOrderFileAdapter(path);
        }
        else if(!name.endsWith(".")) {
            return new NoExtensionOrderFileAdapter(path);
        }
        else {
            throw new IllegalArgumentException("unsupported file format: " + name);
        }
    }
}
