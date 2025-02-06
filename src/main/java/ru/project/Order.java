package ru.project;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.project.Endpoints.GET_ORDER_LIST;

public class Order {
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
