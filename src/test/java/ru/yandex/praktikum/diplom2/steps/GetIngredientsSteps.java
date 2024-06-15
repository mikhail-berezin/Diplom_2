package ru.yandex.praktikum.diplom2.steps;

import io.qameta.allure.Step;
import ru.yandex.praktikum.diplom2.model.Ingredient;
import ru.yandex.praktikum.diplom2.model.IngredientsResponse;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.diplom2.constants.StellarburgerTestConstants.GET_INGREDIENTS_ENDPOINT;

public class GetIngredientsSteps {

    @Step("GET request to /api/ingredients and take first two ingredient hashes")
    public static List<String> getIngredientHashList() {
        List<Ingredient> ingredients = getIngredients();
        List<String> ingredientHashList = new ArrayList<>();
        ingredientHashList.add(ingredients.get(0).get_id());
        ingredientHashList.add(ingredients.get(1).get_id());
        return ingredientHashList;
    }

    private static List<Ingredient> getIngredients() {
        IngredientsResponse ingredientsResponse =
                given()
                        .get(GET_INGREDIENTS_ENDPOINT)
                        .as(IngredientsResponse.class);

        return ingredientsResponse.getData();
    }
}