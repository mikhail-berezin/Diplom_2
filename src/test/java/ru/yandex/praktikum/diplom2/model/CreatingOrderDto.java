package ru.yandex.praktikum.diplom2.model;

import java.util.List;

public class CreatingOrderDto {

    List<String> ingredients;

    public CreatingOrderDto() {
    }

    public CreatingOrderDto(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}