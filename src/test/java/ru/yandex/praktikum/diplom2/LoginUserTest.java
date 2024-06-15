package ru.yandex.praktikum.diplom2;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.diplom2.model.LoginResponse;
import ru.yandex.praktikum.diplom2.model.LoginUserDto;
import ru.yandex.praktikum.diplom2.model.RegisterUserDto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.diplom2.steps.CommonSteps.checkStatusIs200;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.*;
import static ru.yandex.praktikum.diplom2.service.GenerateDataService.generateEmail;
import static ru.yandex.praktikum.diplom2.steps.CommonSteps.checkStatusIs401;
import static ru.yandex.praktikum.diplom2.steps.DeleteUserSteps.deleteUser;
import static ru.yandex.praktikum.diplom2.steps.LoginUserSteps.loginUser;
import static ru.yandex.praktikum.diplom2.steps.RegisterUserSteps.registerUser;

public class LoginUserTest {

    String email;
    String token;

    @Before
    public void setUp() {
        RestAssured.baseURI = STELLARBURGER_URL;
        email = generateEmail();
        Response userRegisterResponse = registerUser(new RegisterUserDto(email, USER_PASSWORD, USER_NAME));
        LoginResponse response = userRegisterResponse.as(LoginResponse.class);
        if (response.isSuccess()) token = response.getAccessToken().substring(7);
    }

    @Test
    @DisplayName("Logging in user status test")
    @Description("Check logging in response status is 200")
    public void loggingInUserStatusTest() {
        Response userLoginResponse = loginUser(new LoginUserDto(email, USER_PASSWORD));

        checkStatusIs200(userLoginResponse);
    }

    @Test
    @DisplayName("Logging in user response test")
    @Description("Check logging in response isSuccess field equals true")
    public void loggingInUserResponseTest() {
        Response userLoginResponse = loginUser(new LoginUserDto(email, USER_PASSWORD));

        LoginResponse loginResponse = userLoginResponse.as(LoginResponse.class);
        assertTrue(loginResponse.isSuccess());
    }

    @Test
    @DisplayName("Logging in incorrect user status test")
    @Description("Check logging in incorrect user response status is 401")
    public void loggingInIncorrectUserStatusTest() {
        Response userLoginResponse = loginUser(new LoginUserDto(email, ANOTHER_PASSWORD));

        checkStatusIs401(userLoginResponse);
    }

    @Test
    @DisplayName("Logging in incorrect user response test")
    @Description("Check logging in incorrect user response isSuccess field equals false")
    public void loggingInIncorrectUserResponseTest() {
        Response userLoginResponse = loginUser(new LoginUserDto(email, ANOTHER_PASSWORD));

        LoginResponse loginResponse = userLoginResponse.as(LoginResponse.class);
        assertFalse(loginResponse.isSuccess());
    }

    @After
    public void clearUser() {
        deleteUser(token);
    }
}