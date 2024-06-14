package ru.yandex.praktikum.diplom2.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.diplom2.model.LoginUserDto;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.JSON_CONTENT_TYPE;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.LOGIN_USER_ENDPOINT;

public class LoginUserSteps {

    @Step("POST request to /api/auth/login")
    public static Response loginUser(LoginUserDto loginUserDto) {
        return given()
                .header(CONTENT_TYPE, JSON_CONTENT_TYPE)
                .and().body(loginUserDto)
                .post(LOGIN_USER_ENDPOINT);
    }
}