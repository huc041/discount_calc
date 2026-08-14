package com.learning.discount_calculator.source.adapter;

import java.nio.file.Path;

public class NoExtensionOrderFileAdapter extends AbstractDelimitedOrderAdapter {
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
