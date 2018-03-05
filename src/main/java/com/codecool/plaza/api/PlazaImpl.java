package com.codecool.plaza.api;

import com.codecool.plaza.api.exceptions.NoSuchShopException;
import com.codecool.plaza.api.exceptions.PlazaIsClosedException;
import com.codecool.plaza.api.exceptions.ShopAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class PlazaImpl implements Plaza {

    private String name;
    private String owner;
    private List<Shop> shops;
    private boolean open;

    public PlazaImpl(String name, String owner, boolean open) {
        shops = new ArrayList<>();
        this.name = name;
        this.owner = owner;
        this.open = open;
    }

    @Override
    public List<Shop> getShops() throws PlazaIsClosedException {
        plazaClosed();
        return shops;
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {
        plazaClosed();
        for (Shop s : shops) {
            if (s.getName().equals(shop.getName())) {
                throw new ShopAlreadyExistsException("This plaza already has a shop with this name!\n");
            }
        }
        shops.add(shop);
    }

    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        plazaClosed();
        if (!shops.contains(shop)) {
            throw new NoSuchShopException("There is no shop in this plaza with this name!\n");
        }
        shops.remove(shop);
    }

    @Override
    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        plazaClosed();
        for (Shop s : shops) {
            if (name.equals(s.getName())) {
                return s;
            }
        }
        throw new NoSuchShopException("There is no shop in this plaza with this name!\n");
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

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public void plazaClosed() throws PlazaIsClosedException {
        if (!isOpen()) {
            throw new PlazaIsClosedException("You have to open the plaza first!\n");
        }
    }
}
