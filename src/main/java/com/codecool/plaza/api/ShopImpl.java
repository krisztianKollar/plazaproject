package com.codecool.plaza.api;

import com.codecool.plaza.api.exceptions.NoSuchProductException;
import com.codecool.plaza.api.exceptions.OutOfStockException;
import com.codecool.plaza.api.exceptions.ProductAlreadyExistsException;
import com.codecool.plaza.api.exceptions.ShopIsClosedException;

import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {

    private String name;
    private String owner;
    private Map<Long, ShopEntryImpl> products;
    private boolean open = false;

    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public void open() { open = true; }

    @Override
    public void close() { open = false; }

    @Override
    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        if (isOpen()) return products.containsKey(barcode);
        else throw new ShopIsClosedException("You can not use this function until the shop is closed!");
    }

    @Override
    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {
//        if (isOpen()) {
//            for (Product p : products.containsValue()){
//                if () {
//                    throw new ProductAlreadyExistsException("This product is already exists");
//                }
//            }
//        }
//        else throw new ShopIsClosedException("You can not use this function until the shop is closed!");

    }

    @Override
    public void addProduct(long barcode, int quantity) throws ShopIsClosedException {

    }

    @Override
    public float getPrice(long barcode) {
        return 0;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    @Override
    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    @Override
    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        return null;
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
