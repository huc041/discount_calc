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

        OrderManager orderManager = new OrderManager(pathInput, pathOutput);
        orderManager.processMethod(discount, stepDiscount,unitPrice);
    }
}
