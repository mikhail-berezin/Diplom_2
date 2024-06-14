package ru.yandex.praktikum.diplom2.constants;

import java.util.Arrays;
import java.util.List;

public class StellarburgerTestConstants {

    public static final String STELLARBURGER_URL = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTER_USER_ENDPOINT = "/api/auth/register";
    public static final String LOGIN_USER_ENDPOINT = "/api/auth/login";
    public static final String EDIT_USER_ENDPOINT = "/api/auth/user";
    public static final String GET_INGREDIENTS_ENDPOINT = "/api/ingredients";
    public static final String ORDERS_ENDPOINT = "/api/orders";

    public static final String USER_EMAIL = "foo020620241245@bar.org";
    public static final String USER_PASSWORD = "Foo1234";
    public static final String ANOTHER_PASSWORD = "Foo4321";
    public static final String USER_NAME = "John";
    public static final String NEW_NAME = "George";

    public static final String EMPTY_STRING = "";

    public static final List<String> INCORRECT_INGREDIENTS = Arrays.asList("a", "b");

    public static final String JSON_CONTENT_TYPE = "application/json";
}