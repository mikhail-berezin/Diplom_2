package ru.yandex.praktikum.diplom2.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.diplom2.model.CreatingOrderDto;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.*;

public class CreateOrderSteps {

    @Step("POST request to /api/orders with authorization")
    public static Response createOrderWithAuthorization(CreatingOrderDto creatingOrderDto, String token) {
        return given()
                .header(CONTENT_TYPE, JSON_CONTENT_TYPE)
                .auth().oauth2(token)
                .and().body(creatingOrderDto)
                .post(ORDERS_ENDPOINT);
    }

    @Step("POST request to /api/orders without authorization")
    public static Response createOrderWithoutAuthorization(CreatingOrderDto creatingOrderDto) {
        return given()
                .header(CONTENT_TYPE, JSON_CONTENT_TYPE)
                .and().body(creatingOrderDto)
                .post(ORDERS_ENDPOINT);
    }
}