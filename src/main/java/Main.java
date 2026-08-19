import com.learning.discount_calculator.service.OrderWriteToFileService;
import com.learning.discount_calculator.manager.OrderManager;
import com.learning.discount_calculator.service.OrderReadService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        Path pathInput = Paths.get(
                //"src/main/resources/discount_day.txt"
                //"src/main/resources/discount_day_without_ext"
                "src/main/resources/orders.bak"
        );
        Path pathOutput = Paths.get("src/main/resources/result.txt");

        BigDecimal discount = BigDecimal.valueOf(0.5);
        BigDecimal stepDiscount = BigDecimal.valueOf(0.05);
        BigDecimal unitPrice = BigDecimal.valueOf(10.0);

        OrderReadService orderReadService = new OrderReadService();
        OrderWriteToFileService orderFileService = new OrderWriteToFileService();

        OrderManager orderManager = new OrderManager(orderReadService, orderFileService);
        orderManager.process(pathInput, pathOutput, discount, stepDiscount,unitPrice);
    }
}
