package com.alexanr.demin.test_app.feature;

import java.util.ArrayList;
import java.util.List;

public class WeatherItems {

    private static final WeatherItems ourInstance = new WeatherItems();

    private List<WeatherItem> itemList;

    private WeatherItems() {
        this.itemList = new ArrayList<>();
    }

    public static WeatherItems getInstance() {
        return ourInstance;
    }

    public List<WeatherItem> getItemList() {
        return this.itemList;
    }

    public void addNewItem(WeatherItem item) {
        this.itemList.add(item);
    }
}
