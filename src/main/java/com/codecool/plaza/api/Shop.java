package com.codecool.plaza.api;

import java.util.List;

public interface Shop {

    String getName() {
        return null;
    }

    String getOwner() {
        return null;
    }

    boolean isOpen() {
        return false;
    }

    void open() {

    }

    void close() {

    }

    Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        return null;
    }

    boolean hasProduct(long barcode) throws ShopIsClosedException {
        return false;
    }

    void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {

    }

    void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {

    }

    Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    float getPrice(long barcode) {
        return price;
    }

    List<Product> getProducts() {
        return null;
    }

    String toString() {
        return null;
    }

}
