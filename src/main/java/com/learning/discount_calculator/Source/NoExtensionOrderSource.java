package com.learning.discount_calculator.Source;

import java.nio.file.Path;

public class NoExtensionOrderSource extends AbstractDelimitedOrderSource {
    @Override
    protected String delimiter() {
        return "#";
    }

    public NoExtensionOrderSource(Path path) {
        super(path);
    }
}
