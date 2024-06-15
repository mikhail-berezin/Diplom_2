package ru.yandex.praktikum.diplom2.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.diplom2.model.RegisterUserDto;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.EDIT_USER_ENDPOINT;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.JSON_CONTENT_TYPE;

public class EditUserSteps {

    @Step("POST request to /api/auth/user with authorization")
    public static Response editUserWithAuthorization(RegisterUserDto registerUserDto, String token) {
        return given()
                .header(CONTENT_TYPE, JSON_CONTENT_TYPE)
                .auth().oauth2(token)
                .and().body(registerUserDto)
                .patch(EDIT_USER_ENDPOINT);
    }

    @Step("POST request to /api/auth/user without authorization")
    public static Response editUserWithoutAuthorization(RegisterUserDto registerUserDto) {
        return given()
                .header(CONTENT_TYPE, JSON_CONTENT_TYPE)
                .and().body(registerUserDto)
                .patch(EDIT_USER_ENDPOINT);
    }
}