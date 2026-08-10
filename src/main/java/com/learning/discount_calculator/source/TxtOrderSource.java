package com.learning.discount_calculator.source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TxtOrderSource extends AbstractDelimitedOrderSource {

    @Override
    protected String delimiter() {
        return "|";
    }

    public TxtOrderSource(Path path) {
        super(path);
    }
}
