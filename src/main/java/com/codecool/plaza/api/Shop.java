package com.codecool.plaza.api;

import com.codecool.plaza.api.exceptions.NoSuchProductException;
import com.codecool.plaza.api.exceptions.OutOfStockException;
import com.codecool.plaza.api.exceptions.ProductAlreadyExistsException;
import com.codecool.plaza.api.exceptions.ShopIsClosedException;

import java.util.List;

public interface Shop {

    String getName();

    String getOwner();

    boolean isOpen();

    void open();

    void close();

    Product findByName(String name) throws NoSuchProductException, ShopIsClosedException;

    boolean hasProduct(long barcode) throws ShopIsClosedException;

    void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException;

    void addProduct(long barcode, int quantity) throws ShopIsClosedException;

    Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException;

    List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException;

    float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException;

    List<Product> getProducts();

    String toString();
}
