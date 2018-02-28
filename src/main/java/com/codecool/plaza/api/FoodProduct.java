package com.codecool.plaza.api;

import java.util.Date;

public class FoodProduct extends Product {

    private int calories;
    private Date bestBefore;

    public FoodProduct(long barcode, String manufacturer, String name, int calories, Date bestBefore) {
        super(barcode, manufacturer, name);
        this.calories = calories;
        this.bestBefore = bestBefore;
    }

    public boolean isStillConsumable() {
        //if (Date.before(bestBefore)) {
            return true;
//        }
//        return false;
    }

    public int getCalories() {
        return calories;
    }

    public String toString() {
        return null;
    }
}
