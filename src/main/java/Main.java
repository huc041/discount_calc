import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        Path pathFile1 = Paths.get(
                "src/main/java/com/learning/discount_calculator/Resources/discount_day.txt"
        );
        Path pathFile2 = Paths.get(
                "src/main/java/com/learning/discount_calculator/Resources/discount_day_without_ext"
        );

        Path pathOutput = Paths.get("result.txt");

        OrderManager orderManager;
        orderManager = new OrderManager(pathFile1, pathOutput, BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.05),BigDecimal.valueOf(10.0));
        orderManager.processMethod();
    }
}
