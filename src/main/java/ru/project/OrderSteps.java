package ru.project;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.project.Endpoints.*;

public class OrderSteps {

    public static RequestSpecification requestSpecification() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(Endpoints.URL);
    }

    @Step("Создание нового заказа")
    public ValidatableResponse orderCreate(NewOrder orderCreateRequest) {
        return requestSpecification()
                .body(orderCreateRequest)
                .post(CREATE_ORDER)
                .then();
    }
}
