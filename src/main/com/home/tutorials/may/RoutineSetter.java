package com.home.tutorials.may;

public class RoutineSetter {
    static void routineForDog(Dog d) {
        d.eats();
        d.walks();
    }

    static void routineForLion(Lion d) {
        d.eats();
        d.walks();
    }

    static void routineForAnimals(Animal animal) {
        animal.eats();  //Dynamic Binding = Binding of the method call to the actual method that needs to be run
        //  at runtime.. This actual method is nothing but the mehthod of object that ref. points to.
        animal.walks();
    }

    public static void main(String[] args) {
        Dog d = new Dog();

        Animal a = d;     //// Polymorphic Reference

        routineForDog(d);  //Method.parameter.d = main.d
        Lion l = new Lion();
        a = l;
        routineForLion(l); // Method Parameter d = l

        routineForAnimals(d);//  Method param animal = d;
        routineForAnimals(l); // Method param animal = l;

        routineForAnimals(new Animal() {

            @Override
            public void walks() {
                System.out.println("CAT WALKS");
            }

            @Override
            public void eats() {
                System.out.println("CAT EATS");
            }
        });
        Dog d2 = new Dog();

    }
}
