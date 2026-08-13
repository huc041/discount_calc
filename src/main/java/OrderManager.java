import com.learning.discount_calculator.model.Order;
import com.learning.discount_calculator.output.ResultWriter;
import com.learning.discount_calculator.pricing.DiscountCalculator;
import com.learning.discount_calculator.source.OrderSourceFactory;
import com.learning.discount_calculator.source.OrderSourceInterface;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OrderManager {

    private Path pathIn;
    private Path pathOut;

    private BigDecimal discount;
    private BigDecimal step;
    private BigDecimal unitPrice;

    public OrderManager (Path pathInput, Path pathOuput, BigDecimal startDiscount, BigDecimal stepDiscount, BigDecimal unitPrice){

        this.pathIn = pathInput;
        this.pathOut = pathOuput;

        this.discount = startDiscount;
        this.step = stepDiscount;
        this.unitPrice = unitPrice;
    }

    public void processMethod() throws IOException {

        OrderSourceInterface orderSourceInterface = OrderSourceFactory.create(pathIn);
        List<Order> orders = orderSourceInterface.read();
        orders.sort(Comparator.comparing(Order::getOrderDate));

        DiscountCalculator discountCalculator = new DiscountCalculator(BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.05), BigDecimal.valueOf(10.0));
        Map<String, BigDecimal> finalMap = discountCalculator.calculateDiscount(orders);

        ResultWriter rw = new ResultWriter();
        rw.writeDataToFile(finalMap, pathOut);
    }
}


