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
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.*;
import static ru.yandex.praktikum.diplom2.service.GenerateDataService.generateEmail;
import static ru.yandex.praktikum.diplom2.steps.CommonSteps.checkStatusIs200;
import static ru.yandex.praktikum.diplom2.steps.CommonSteps.checkStatusIs401;
import static ru.yandex.praktikum.diplom2.steps.DeleteUserSteps.deleteUser;
import static ru.yandex.praktikum.diplom2.steps.EditUserSteps.editUserWithAuthorization;
import static ru.yandex.praktikum.diplom2.steps.EditUserSteps.editUserWithoutAuthorization;
import static ru.yandex.praktikum.diplom2.steps.RegisterUserSteps.registerUser;

public class EditUserTest {

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
    @DisplayName("Edit user email status test")
    @Description("Check editing user email response status is 200")
    public void editUserEmailStatusTest() {
        String newEmail = generateEmail();
        Response response = editUserWithAuthorization(new RegisterUserDto(newEmail, USER_PASSWORD, USER_NAME), token);
        checkStatusIs200(response);
    }

    @Test
    @DisplayName("Edit user email response test")
    @Description("Check editing user email response contains new email")
    public void editUserEmailResponseTest() {
        String newEmail = generateEmail();
        Response response = editUserWithAuthorization(new RegisterUserDto(newEmail, USER_PASSWORD, USER_NAME), token);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertEquals(loginResponse.getUser().getEmail(), newEmail);
    }

    @Test
    @DisplayName("Edit user password status test")
    @Description("Check editing user password response status is 200")
    public void editUserPasswordStatusTest() {
        Response response = editUserWithAuthorization(new RegisterUserDto(email, ANOTHER_PASSWORD, USER_NAME), token);
        checkStatusIs200(response);
    }

    @Test
    @DisplayName("Edit user password response test")
    @Description("Check editing user password response isSuccess field equals true")
    public void editUserPasswordResponseTest() {
        Response response = editUserWithAuthorization(new RegisterUserDto(email, ANOTHER_PASSWORD, USER_NAME), token);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertTrue(loginResponse.isSuccess());
    }

    @Test
    @DisplayName("Edit user name status test")
    @Description("Check editing user name response status is 200")
    public void editUserNameStatusTest() {
        Response response = editUserWithAuthorization(new RegisterUserDto(email, USER_PASSWORD, NEW_NAME), token);
        checkStatusIs200(response);
    }

    @Test
    @DisplayName("Edit user name response test")
    @Description("Check editing user name response contains new name")
    public void editUserNameResponseTest() {
        Response response = editUserWithAuthorization(new RegisterUserDto(email, USER_PASSWORD, NEW_NAME), token);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertEquals(loginResponse.getUser().getName(), NEW_NAME);
    }

    @Test
    @DisplayName("Edit user email without authorization status test")
    @Description("Check editing user email without authorization response status is 401")
    public void editUserEmailEmailWithoutAuthorizationStatusTest() {
        Response response = editUserWithoutAuthorization(new RegisterUserDto(generateEmail(), USER_PASSWORD, USER_NAME));
        checkStatusIs401(response);
    }

    @Test
    @DisplayName("Edit user email without authorization response test")
    @Description("Check editing user email without authorization response isSuccess field equals false")
    public void editUserEmailWithoutAuthorizationResponseTest() {
        Response response = editUserWithoutAuthorization(new RegisterUserDto(generateEmail(), USER_PASSWORD, USER_NAME));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertFalse(loginResponse.isSuccess());
    }

    @Test
    @DisplayName("Edit user password without authorization status test")
    @Description("Check editing user password without authorization response status is 401")
    public void editUserPasswordWithoutAuthorizationStatusTest() {
        Response response = editUserWithoutAuthorization(new RegisterUserDto(email, ANOTHER_PASSWORD, USER_NAME));
        checkStatusIs401(response);
    }

    @Test
    @DisplayName("Edit user password without authorization response test")
    @Description("Check editing user password without authorization response isSuccess field equals false")
    public void editUserPasswordWithoutAuthorizationResponseTest() {
        Response response = editUserWithoutAuthorization(new RegisterUserDto(email, ANOTHER_PASSWORD, USER_NAME));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertFalse(loginResponse.isSuccess());
    }

    @Test
    @DisplayName("Edit user name without authorization status test")
    @Description("Check editing user name without authorization response status is 401")
    public void editUserNameWithoutAuthorizationStatusTest() {
        Response response = editUserWithoutAuthorization(new RegisterUserDto(email, USER_PASSWORD, NEW_NAME));
        checkStatusIs401(response);
    }

    @Test
    @DisplayName("Edit user name without authorization response test")
    @Description("Check editing user name without authorization response isSuccess field equals false")
    public void editUserNameWithoutAuthorizationResponseTest() {
        Response response = editUserWithoutAuthorization(new RegisterUserDto(email, USER_PASSWORD, NEW_NAME));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertFalse(loginResponse.isSuccess());
    }

    @After
    public void clearUser() {
        deleteUser(token);
    }
}