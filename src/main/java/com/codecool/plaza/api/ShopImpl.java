package com.codecool.plaza.api;

import com.codecool.plaza.api.exceptions.NoSuchProductException;
import com.codecool.plaza.api.exceptions.OutOfStockException;
import com.codecool.plaza.api.exceptions.ProductAlreadyExistsException;
import com.codecool.plaza.api.exceptions.ShopIsClosedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {

    private String name;
    private String owner;
    private Map<Long, ShopImplEntry> products = new HashMap<>();
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
    public void open() {
        open = true;
    }

    @Override
    public void close() {
        open = false;
    }

    @Override
    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        shopClosed();
        return products.containsKey(barcode);
    }

    @Override
    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {
        shopClosed();
        for (ShopImplEntry productEntry : products.values()) {
            if (productEntry.getProduct().getBarcode() == product.getBarcode()) {
                throw new ProductAlreadyExistsException("The shop already has a product with this barcode.\n");
            }
        }
        products.put(product.getBarcode(), new ShopImplEntry(product, quantity, price));
    }

    @Override
    public void addProduct(long barcode, int quantity) throws ShopIsClosedException {
        shopClosed();
        // barcode csekk: ha van ilyen, akkor új terméket akarsz!
        for (Map.Entry<Long, ShopImplEntry> entry : products.entrySet()) {
            if (entry.getKey() == barcode) {
                entry.getValue().increaseQuantity(quantity);
            }
        }
    }

    @Override
    public float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException {
        shopClosed();
        for (Map.Entry<Long, ShopImplEntry> entry : products.entrySet()) {
            if (entry.getKey() == barcode) {
                return entry.getValue().getPrice();
            }
        }
        throw new NoSuchProductException("There is no product in this shop with this barcode!\n");
    }

    @Override
    public List<Product> getProducts() {
        List<Product> allProducts = new ArrayList<>();
        for (ShopImplEntry temp : products.values()) {
            allProducts.add(temp.getProduct());
        }

        return allProducts;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        shopClosed();
        if (!hasProduct(barcode)) {
            throw new NoSuchProductException("There is no product with this barcode at this shop.\n");
        }
        for (Map.Entry<Long, ShopImplEntry> entry : products.entrySet()) {
            if (entry.getKey() == barcode)
                if (entry.getValue().getQuantity() == 0) {
                    throw new OutOfStockException("This product is not available now.\n");
                } else {
                    entry.getValue().decreaseQuantity(1);
                    return entry.getValue().getProduct();
                }
        }
        throw new OutOfStockException("This product is not available now.\n");
    }


    @Override
    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        shopClosed();

        return null;
    }

    @Override
    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        shopClosed();
        for (ShopImplEntry sie : products.values()) {
            if (sie.getProduct().getName().equals(name)) {
                return sie.getProduct();
            }
        }
        throw new NoSuchProductException("There is no such product in this shop with this name!\n");
    }

    public void shopClosed() throws ShopIsClosedException {
        if (!isOpen()) {
            throw new ShopIsClosedException("Shop is closed: You have to open it first!\n");
        }
    }

    @Override
    public String toString() {
        return products.values().toString();
    }

    private class ShopImplEntry {

        private Product product;
        private int quantity;
        private float price;

        public ShopImplEntry(Product product, int quantity, float price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
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

        @Override
        public String toString() {
            return "Barcode: " + product.getBarcode() + ",\tname: " + product.getName() + ",\tquantity: " + quantity + ",\tprice: " + price;
        }
    }

}
