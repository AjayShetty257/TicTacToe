package com.home.tutorials.may;

public class Dog implements Animal {
    @Override
    public void walks() {
        System.out.println("Dog walks");
    }

    @Override
    public void eats() {
        System.out.println("Dog eats");
    }

    public void barks() {
        System.out.println("Bhow Bhow");
    }
}
