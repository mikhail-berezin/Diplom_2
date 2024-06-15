package ru.yandex.praktikum.diplom2.model;

import java.util.List;

public class OrderResponse {

    boolean success;
    String name;
    Order order;

    public OrderResponse(boolean success, String name, Order order) {
        this.success = success;
        this.name = name;
        this.order = order;
    }

    public OrderResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static class Order {
        List<Ingredient> ingredients;
        String _id;
        Owner owner;
        String status;
        String name;
        String createdAt;
        String updatedAt;
        int number;
        int price;

        public Order(
                List<Ingredient> ingredients,
                String _id,
                Owner owner,
                String status,
                String name,
                String createdAt,
                String updatedAt,
                int number,
                int price
        ) {
            this.ingredients = ingredients;
            this._id = _id;
            this.owner = owner;
            this.status = status;
            this.name = name;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.number = number;
            this.price = price;
        }

        public Order() {
        }

        public List<Ingredient> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public Owner getOwner() {
            return owner;
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public static class Owner {
            String name;
            String email;
            String createdAt;
            String updatedAt;

            public Owner(String name, String email, String createdAt, String updatedAt) {
                this.name = name;
                this.email = email;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
            }

            public Owner() {
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
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
        }
    }
}