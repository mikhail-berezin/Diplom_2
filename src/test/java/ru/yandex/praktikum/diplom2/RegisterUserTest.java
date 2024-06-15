package ru.yandex.praktikum.diplom2;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.diplom2.model.LoginResponse;
import ru.yandex.praktikum.diplom2.model.RegisterUserDto;

import static org.junit.Assert.*;
import static ru.yandex.praktikum.diplom2.steps.CommonSteps.*;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.*;
import static ru.yandex.praktikum.diplom2.service.GenerateDataService.generateEmail;
import static ru.yandex.praktikum.diplom2.steps.DeleteUserSteps.deleteUser;
import static ru.yandex.praktikum.diplom2.steps.RegisterUserSteps.registerUser;

public class RegisterUserTest {

    String email;
    String token;

    @Before
    public void setUp() {
        RestAssured.baseURI = STELLARBURGER_URL;
        email = generateEmail();
    }

    @Test
    @DisplayName("Register user status test")
    @Description("Check register user response status is 200")
    public void registerUserStatusTest() {
        Response userRegisterResponse = registerUser(new RegisterUserDto(email, USER_PASSWORD, USER_NAME));
        LoginResponse response = userRegisterResponse.as(LoginResponse.class);
        if (response.isSuccess()) token = response.getAccessToken().substring(7);
        checkStatusIs200(userRegisterResponse);
    }

    @Test
    @DisplayName("Register user response test")
    @Description("Check register user response isSuccess field equals true")
    public void registerUserResponseTest() {
        Response userRegisterResponse = registerUser(new RegisterUserDto(email, USER_EMAIL, USER_NAME));
        LoginResponse response = userRegisterResponse.as(LoginResponse.class);
        if (response.isSuccess()) token = response.getAccessToken().substring(7);
        assertTrue(response.isSuccess());
    }

    @Test
    @DisplayName("Register same user status test")
    @Description("Check register same user response status is 403")
    public void registerSameUserStatusTest() {
        Response firstUserRegisterResponse = registerUser(new RegisterUserDto(email, USER_EMAIL, USER_NAME));
        LoginResponse firstLoginResponse = firstUserRegisterResponse.as(LoginResponse.class);
        if (firstLoginResponse.isSuccess()) token = firstLoginResponse.getAccessToken().substring(7);

        Response secondUserRegisterResponse = registerUser(new RegisterUserDto(email, USER_EMAIL, USER_NAME));
        checkStatusIs403(secondUserRegisterResponse);
    }

    @Test
    @DisplayName("Register same user response test")
    @Description("Check register same user response isSuccess field equals false")
    public void registerSameUsersResponseTest() {
        Response firstUserRegisterResponse = registerUser(new RegisterUserDto(email, USER_EMAIL, USER_NAME));
        LoginResponse firstLoginResponse = firstUserRegisterResponse.as(LoginResponse.class);
        if (firstLoginResponse.isSuccess()) token = firstLoginResponse.getAccessToken().substring(7);

        Response secondUserRegisterResponse = registerUser(new RegisterUserDto(email, USER_EMAIL, USER_NAME));
        assertFalse(secondUserRegisterResponse.as(LoginResponse.class).isSuccess());
    }

    @Test
    @DisplayName("Register incorrect user status test")
    @Description("Check register same user response status is 403")
    public void registerIncorrectUserStatusTest() {
        Response userRegisterResponse = registerUser(new RegisterUserDto(email, EMPTY_STRING, USER_NAME));
        LoginResponse response = userRegisterResponse.as(LoginResponse.class);
        if (response.isSuccess()) token = response.getAccessToken().substring(7);
        checkStatusIs403(userRegisterResponse);
    }

    @Test
    @DisplayName("Register incorrect user status test")
    @Description("Check register same user response isSuccess field equals false")
    public void registerIncorrectUserResponseTest() {
        Response userRegisterResponse = registerUser(new RegisterUserDto(email, EMPTY_STRING, USER_NAME));
        LoginResponse response = userRegisterResponse.as(LoginResponse.class);
        if (response.isSuccess()) token = response.getAccessToken().substring(7);
        assertFalse(response.isSuccess());
    }

    @After
    public void clearUser() {
        deleteUser(token);
    }
}