import com.learning.discount_calculator.output.OrderFileService;
import com.learning.discount_calculator.service.OrderManager;
import com.learning.discount_calculator.service.OrderService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        Path pathInput = Paths.get(
                "src/main/java/com/learning/discount_calculator/Resources/discount_day.txt"
                //"src/main/java/com/learning/discount_calculator/Resources/discount_day_without_ext"
        );
        Path pathOutput = Paths.get("result.txt");

        BigDecimal discount = BigDecimal.valueOf(0.5);
        BigDecimal stepDiscount = BigDecimal.valueOf(0.05);
        BigDecimal unitPrice = BigDecimal.valueOf(10.0);

        OrderService orderService = new OrderService();
        OrderFileService orderFileService = new OrderFileService();

        OrderManager orderManager = new OrderManager(orderService, orderFileService);
        orderManager.process(pathInput, pathOutput, discount, stepDiscount,unitPrice);
    }
}
