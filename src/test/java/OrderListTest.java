import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.project.Order;

public class OrderListTest {

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Все активные/завершенные заказы курьера")
    public void orders() {
        Order order = new Order();
        order.orderGetList();
    }
}