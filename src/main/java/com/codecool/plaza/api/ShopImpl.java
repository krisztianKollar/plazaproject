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
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {

        }

        public void increaseQuantity(int amount) {

        }

        public void decreaseQuantity(int amount) {

        }

        public float getPrice() {
            return price;
        }

        public void setPrice(int price) {

        }

        public String toString() {
            return null;
        }

    }
}
