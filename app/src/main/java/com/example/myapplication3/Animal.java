package com.example.myapplication3;

public class Animal {

    private final String name;
    private final int imageRes;

    public Animal(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public int getImageRes() {
        return imageRes;
    }
}
