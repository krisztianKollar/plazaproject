package com.codecool.plaza.api;

import java.util.Map;

public class ShopImpl implements Shop {

    private String name;
    private String owner;
    private Map<Long, ShopEntryImpl> products;

    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String getName() {
        return name;
    }

    private class ShopEntryImpl {

        private Product product;
        private int quantity;
        private float price;

        public ShopEntryImpl(Product product, int quantity, float price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void increaseQuantity(int amount) {
            quantity += amount;
        }

        public void decreaseQuantity(int amount) {
            quantity -= amount;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String toString() {
            return null;
        }
    }
}
