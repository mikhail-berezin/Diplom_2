package ru.yandex.praktikum.diplom2;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.diplom2.model.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.*;
import static ru.yandex.praktikum.diplom2.service.GenerateDataService.generateEmail;
import static ru.yandex.praktikum.diplom2.steps.CommonSteps.*;
import static ru.yandex.praktikum.diplom2.steps.CreateOrderSteps.createOrderWithAuthorization;
import static ru.yandex.praktikum.diplom2.steps.DeleteUserSteps.deleteUser;
import static ru.yandex.praktikum.diplom2.steps.GetIngredientsSteps.getIngredientHashList;
import static ru.yandex.praktikum.diplom2.steps.GetOrdersSteps.getOrdersWithAuthorization;
import static ru.yandex.praktikum.diplom2.steps.GetOrdersSteps.getOrdersWithoutAuthorization;
import static ru.yandex.praktikum.diplom2.steps.RegisterUserSteps.registerUser;

public class GetOrdersTest {

    String token;

    @Before
    public void setUp() {
        RestAssured.baseURI = STELLARBURGER_URL;
        String email = generateEmail();
        Response userRegisterResponse = registerUser(new RegisterUserDto(email, USER_PASSWORD, USER_NAME));
        LoginResponse response = userRegisterResponse.as(LoginResponse.class);
        if (response.isSuccess()) token = response.getAccessToken().substring(7);

        CreatingOrderDto creatingOrderDto = new CreatingOrderDto(getIngredientHashList());
        createOrderWithAuthorization(creatingOrderDto, token);
    }

    @Test
    @DisplayName("Create order with authorization status test")
    @Description("Check creating order with authorization response status is 200")
    public void createOrderWithAuthorizationStatusTest() {
        Response response = getOrdersWithAuthorization(token);
        checkStatusIs200(response);
    }

    @Test
    @DisplayName("Create order with authorization response test")
    @Description("Check creating order with authorization response isSuccess field equals true")
    public void createOrderWithAuthorizationResponseTest() {
        Response response = getOrdersWithAuthorization(token);
        OrdersResponse ordersResponse = response.as(OrdersResponse.class);
        assertTrue(ordersResponse.isSuccess());
    }

    @Test
    @DisplayName("Create order without authorization status test")
    @Description("Check creating order without authorization response status is 401")
    public void createOrderWithoutAuthorizationStatusTest() {
        Response response = getOrdersWithoutAuthorization();
        checkStatusIs401(response);
    }

    @Test
    @DisplayName("Create order without authorization response test")
    @Description("Check creating order without authorization response isSuccess field equals false")
    public void createOrderWithoutAuthorizationResponseTest() {
        Response response = getOrdersWithoutAuthorization();
        OrdersResponse ordersResponse = response.as(OrdersResponse.class);
        assertFalse(ordersResponse.isSuccess());
    }

    @After
    public void clearUser() {
        deleteUser(token);
    }
}