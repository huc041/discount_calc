import com.learning.discount_calculator.Model.Order;
import com.learning.discount_calculator.Output.ResultWriter;
import com.learning.discount_calculator.Pricing.DiscountCalculator;
import com.learning.discount_calculator.Source.OrderSourceFactory;
import com.learning.discount_calculator.Source.OrderSourceInterface;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        Path pathFile1 = Paths.get(
                "src/main/java/com/learning/discount_calculator/Resources/discount_day.txt"
        );
        Path pathFile2 = Paths.get(
                "src/main/java/com/learning/discount_calculator/Resources/discount_day_without_ext"
        );

        OrderSourceInterface orderSourceInterface = OrderSourceFactory.create(pathFile1);
        List<Order> orders = orderSourceInterface.read();
        orders.sort(Comparator.comparing(Order::getOrderDate));

        DiscountCalculator discountCalculator = new DiscountCalculator(BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.05), BigDecimal.valueOf(10.0));
        Map <String, BigDecimal> finalMap = discountCalculator.calculateDiscount(orders);

        Path pathFile = Paths.get("result.txt");
        ResultWriter rw = new ResultWriter();
        rw.writeDataToFile(finalMap, pathFile);
    }
}
