package com.learning.discount_calculator.source;

import java.nio.file.Path;

public class TxtOrderFileAdapter extends AbstractDelimitedOrderSource {

    @Override
    protected String delimiter() {
        return "|";
    }

    @Override
    protected String datePattern(){
        return "yyyy-MM-dd'T'HH:mm:ss";
    }

    public TxtOrderFileAdapter(Path path) {
        super(path);
    }
}
