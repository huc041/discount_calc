import com.learning.discount_calculator.model.Order;
import com.learning.discount_calculator.output.ResultWriter;
import com.learning.discount_calculator.pricing.DiscountCalculator;
import com.learning.discount_calculator.source.OrderSourceFactory;
import com.learning.discount_calculator.source.OrderSourceInterface;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class OrderManager {

    private Path pathIn;
    private Path pathOut;

    public OrderManager (Path pathInput, Path pathOuput){

        this.pathIn = pathInput;
        this.pathOut = pathOuput;
    }

    public void process (BigDecimal startDiscount, BigDecimal stepDiscount, BigDecimal unitPrice) throws IOException {

        OrderSourceInterface orderSourceInterface = OrderSourceFactory.create(pathIn);
        List<Order> orders = orderSourceInterface.read();

        DiscountCalculator discountCalculator = new DiscountCalculator(startDiscount, stepDiscount, unitPrice);
        Map<String, BigDecimal> finalMap = discountCalculator.calculateDiscount(orders);

        ResultWriter rw = new ResultWriter();
        rw.writeDataToFile(finalMap, pathOut);
    }
}


