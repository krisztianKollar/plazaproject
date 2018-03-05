package com.codecool.plaza.api;


public class FoodProduct extends Product {

    private int calories;

    public FoodProduct(long barcode, String manufacturer, String name, int calories) {
        super(barcode, manufacturer, name);
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public String toString() {
        return null;
    }
}
