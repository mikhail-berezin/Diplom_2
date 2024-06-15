package ru.yandex.praktikum.diplom2.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.*;

public class CommonSteps {

    @Step("Check response status is 200")
    public static void checkStatusIs200(Response response) {
        response.then().statusCode(SC_OK);
    }

    @Step("Check response status is 400")
    public static void checkStatusIs400(Response response) {
        response.then().statusCode(SC_BAD_REQUEST);
    }

    @Step("Check response status is 401")
    public static void checkStatusIs401(Response response) {
        response.then().statusCode(SC_UNAUTHORIZED);
    }

    @Step("Check response status is 403")
    public static void checkStatusIs403(Response response) {
        response.then().statusCode(SC_FORBIDDEN);
    }

    @Step("Check response status is 409")
    public static void checkStatusIs500(Response response) {
        response.then().statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}