package ru.yandex.praktikum.diplom2;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.diplom2.model.*;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.*;
import static ru.yandex.praktikum.diplom2.service.GenerateDataService.generateEmail;
import static ru.yandex.praktikum.diplom2.steps.CommonSteps.*;
import static ru.yandex.praktikum.diplom2.steps.CreateOrderSteps.createOrderWithAuthorization;
import static ru.yandex.praktikum.diplom2.steps.CreateOrderSteps.createOrderWithoutAuthorization;
import static ru.yandex.praktikum.diplom2.steps.DeleteUserSteps.deleteUser;
import static ru.yandex.praktikum.diplom2.steps.GetIngredientsSteps.getIngredientHashList;
import static ru.yandex.praktikum.diplom2.steps.RegisterUserSteps.registerUser;

public class CreateOrderTest {

    String token;

    @Before
    public void setUp() {
        RestAssured.baseURI = STELLARBURGER_URL;
        String email = generateEmail();
        Response userRegisterResponse = registerUser(new RegisterUserDto(email, USER_PASSWORD, USER_NAME));
        LoginResponse response = userRegisterResponse.as(LoginResponse.class);
        if (response.isSuccess()) token = response.getAccessToken().substring(7);
    }

    @Test
    @DisplayName("Create order with authorization status test")
    @Description("Check creating order with authorization response status is 200")
    public void createOrderWithAuthorizationStatusTest() {
        CreatingOrderDto creatingOrderDto = new CreatingOrderDto(getIngredientHashList());
        Response response = createOrderWithAuthorization(creatingOrderDto, token);
        checkStatusIs200(response);
    }

    @Test
    @DisplayName("Create order with authorization response test")
    @Description("Check creating order with authorization response isSuccess field equals true")
    public void  createOrderWithAuthorizationResponseTest() {
        CreatingOrderDto creatingOrderDto = new CreatingOrderDto(getIngredientHashList());
        Response response = createOrderWithAuthorization(creatingOrderDto, token);

        OrderResponse orderResponse = response.as(OrderResponse.class);
        assertTrue(orderResponse.isSuccess());
    }

    @Test
    @DisplayName("Create order without authorization status test")
    @Description("Check creating order without authorization response status is 200")
    public void createOrderWithoutAuthorizationStatusTest() {
        CreatingOrderDto creatingOrderDto = new CreatingOrderDto(getIngredientHashList());
        Response response = createOrderWithoutAuthorization(creatingOrderDto);
        checkStatusIs200(response);
    }

    @Test
    @DisplayName("Create order without authorization response test")
    @Description("Check creating order without authorization response isSuccess field equals true")
    public void  createOrderWithoutAuthorizationResponseTest() {
        CreatingOrderDto creatingOrderDto = new CreatingOrderDto(getIngredientHashList());
        Response response = createOrderWithoutAuthorization(creatingOrderDto);

        OrderResponse orderResponse = response.as(OrderResponse.class);
        assertTrue(orderResponse.isSuccess());
    }

    @Test
    @DisplayName("Create order with ingredients status test")
    @Description("Check creating order with ingredients response status is 200")
    public void createOrderWithIngredientsStatusTest() {
        CreatingOrderDto creatingOrderDto = new CreatingOrderDto(getIngredientHashList());
        Response response = createOrderWithAuthorization(creatingOrderDto, token);
        checkStatusIs200(response);
    }

    @Test
    @DisplayName("Create order with ingredients response test")
    @Description("Check creating order with ingredients response isSuccess field equals true")
    public void  createOrderWithIngredientsResponseTest() {
        CreatingOrderDto creatingOrderDto = new CreatingOrderDto(getIngredientHashList());
        Response response = createOrderWithAuthorization(creatingOrderDto, token);

        OrderResponse orderResponse = response.as(OrderResponse.class);
        assertTrue(orderResponse.isSuccess());
    }

    @Test
    @DisplayName("Create order without ingredients status test")
    @Description("Check creating order without ingredients response status is 400")
    public void createOrderWithoutIngredientsStatusTest() {
        CreatingOrderDto creatingOrderDto = new CreatingOrderDto(new ArrayList<>());
        Response response = createOrderWithAuthorization(creatingOrderDto, token);
        checkStatusIs400(response);
    }

    @Test
    @DisplayName("Create order without ingredients response test")
    @Description("Check creating order without ingredients response isSuccess field equals false")
    public void  createOrderWithoutIngredientsResponseTest() {
        CreatingOrderDto creatingOrderDto = new CreatingOrderDto(new ArrayList<>());
        Response response = createOrderWithAuthorization(creatingOrderDto, token);

        OrderResponse orderResponse = response.as(OrderResponse.class);
        assertFalse(orderResponse.isSuccess());
    }

    @Test
    @DisplayName("Create order with incorrect ingredients status test")
    @Description("Check creating order with incorrect ingredients response status is 500")
    public void createOrderWithIncorrectIngredientsStatusTest() {
        CreatingOrderDto creatingOrderDto = new CreatingOrderDto(INCORRECT_INGREDIENTS);
        Response response = createOrderWithAuthorization(creatingOrderDto, token);
        checkStatusIs500(response);
    }

    @After
    public void clearUser() {
        deleteUser(token);
    }
}