package ru.yandex.praktikum.diplom2.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.ORDERS_ENDPOINT;

public class GetOrdersSteps {

    @Step("GET request to /api/orders with authorization")
    public static Response getOrdersWithAuthorization(String token) {
        return given().auth().oauth2(token).get(ORDERS_ENDPOINT);
    }

    @Step("GET request to /api/orders without authorization")
    public static Response getOrdersWithoutAuthorization() {
        return given().get(ORDERS_ENDPOINT);
    }
}