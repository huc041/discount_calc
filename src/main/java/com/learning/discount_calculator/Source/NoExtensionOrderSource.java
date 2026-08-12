package com.learning.discount_calculator.Source;

import java.nio.file.Path;

public class NoExtensionOrderSource extends AbstractDelimitedOrderSource {
    @Override
    protected String delimiter() {
        return "#";
    }

    @Override
    protected String datePattern(){
        return "yyyy-MM-dd 'T'HH:mm:ss";
    }

    public NoExtensionOrderSource(Path path) {
        super(path);
    }
}
