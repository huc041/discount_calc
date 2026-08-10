package com.learning.discount_calculator.Source;

import java.nio.file.Path;

public class TxtOrderSource extends AbstractDelimitedOrderSource {

    @Override
    protected String delimiter() {
        return "|";
    }

    public TxtOrderSource(Path path) {
        super(path);
    }
}
