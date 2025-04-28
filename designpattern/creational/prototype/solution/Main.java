package io.dowlath.designpattern.creational.prototype.solution;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Dowlath",45);
        System.out.println("Person 1: "+ person);

        Person secondPerson = (Person) person.clone();
        System.out.println("Person copy...: "+ secondPerson);

        System.out.println(System.identityHashCode(person) + "\n" + System.identityHashCode(secondPerson));
    }
}
