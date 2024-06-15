package ru.yandex.praktikum.diplom2.steps;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.EDIT_USER_ENDPOINT;

public class DeleteUserSteps {

    @Step("DELETE request to /api/auth/user with authorization")
    public static void deleteUser(String token) {
        if (token != null) {
            given().auth().oauth2(token).delete(EDIT_USER_ENDPOINT);
        }
    }
}
