package ru.yandex.praktikum.diplom2.model;

import java.util.List;

public class OrdersResponse {

    boolean success;
    List<Order> orders;
    int total;
    int totalToday;

    public OrdersResponse(boolean success, List<Order> orders, int total, int totalToday) {
        this.success = success;
        this.orders = orders;
        this.total = total;
        this.totalToday = totalToday;
    }

    public OrdersResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(int totalToday) {
        this.totalToday = totalToday;
    }

    public static class Order {
        String _id;
        List<String> ingredients;
        String status;
        String name;
        String createdAt;
        String updatedAt;
        int number;

        public Order(
                String _id,
                List<String> ingredients,
                String status,
                String name,
                String createdAt,
                String updatedAt,
                int number
        ) {
            this._id = _id;
            this.ingredients = ingredients;
            this.status = status;
            this.name = name;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.number = number;
        }

        public Order() {
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public List<String> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}