package io.dowlath.designpattern.creational.prototype;

public class Main {
    public static void main(String[] args) {

        Person dowlath = new Person("Dowlath",31);
        System.out.println("Person 1: "+ dowlath);

        Person basha = (Person)dowlath.clone();
        basha.setName("Basha");
        System.out.println("Person 2: "+ basha);

        Dolphin jerry = new Dolphin("Jerry",90);
        System.out.println("Dolphin 1: "+jerry);

        Dolphin sam = (Dolphin) jerry.clone();
        sam.setName("Sam");
        System.out.println("Dolphin 2: "+ sam);
    }
}
