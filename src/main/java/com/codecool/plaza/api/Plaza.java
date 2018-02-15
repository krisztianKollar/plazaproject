package com.codecool.plaza.api;

public interface Plaza {

    List<Shop> getShops() throws PlazaIsClosedException {
        return null;
    }

    void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {

    }

    void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {

    }

    Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        return null;
    }

    boolean isOpen() {
        return false;
    }

    void open() {

    }

    void close() {

    }

    String toString() {
        return null;
    }
}
