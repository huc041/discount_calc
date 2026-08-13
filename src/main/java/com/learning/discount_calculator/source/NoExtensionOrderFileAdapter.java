package com.learning.discount_calculator.source;

import java.nio.file.Path;

public class NoExtensionOrderFileAdapter extends AbstractDelimitedOrderSource {
    @Override
    protected String delimiter() {
        return "#";
    }

    @Override
    protected String datePattern(){
        return "yyyy-MM-dd 'T'HH:mm:ss";
    }

    public NoExtensionOrderFileAdapter(Path path) {
        super(path);
    }
}
