import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import ru.project.Endpoints;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static ru.project.Endpoints.GET_ORDER_LIST;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Все активные/завершенные заказы курьера")
    public void orderGetList() {
        given().log().all()
                .baseUri(Endpoints.URL)
                .get(GET_ORDER_LIST)
                .then()
                .assertThat().body("orders", notNullValue())
                .and()
                .statusCode(200);
    }
}
