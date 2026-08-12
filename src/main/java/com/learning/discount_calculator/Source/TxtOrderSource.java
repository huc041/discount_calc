package com.learning.discount_calculator.Source;

import java.nio.file.Path;

public class TxtOrderSource extends AbstractDelimitedOrderSource {

    @Override
    protected String delimiter() {
        return "|";
    }

    @Override
    protected String datePattern(){
        return "yyyy-MM-dd'T'HH:mm:ss";
    }

    public TxtOrderSource(Path path) {
        super(path);
    }
}
