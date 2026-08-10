import com.learning.discount_calculator.Model.Order;
import com.learning.discount_calculator.Source.OrderSourceFactory;
import com.learning.discount_calculator.Source.OrderSourceInterface;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

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
        for(Order order: orders){
            System.out.println(order.getCompanyMane() +" - " + order.getOrderDate().toString());
        }

        // OrderSourceFactory вызываем метод с параметром Path файла, в ответ
        // даем коллекцию объектов Orders

        // конкретный адаптер TxtOrderSource или NoExtensionOrderSource разбирает
        // файл по разделителям и записывает результат в коллекцию Orders

        // далее должен быть отдельный класс расчета скидок DiscountEstimator,
        // который принимает объекты Orders и возвращает Map (key - companyName,
        // value - totalPrice)

        // отдельный класс WriteDataToFile который принимает Map(companyName, totalPrice)
        // и возвращает например Path файла записи, или boolean переменную с результатом
        // записи по заранее известному пути
    }
}
