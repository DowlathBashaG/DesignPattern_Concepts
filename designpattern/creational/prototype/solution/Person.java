package io.dowlath.designpattern.creational.prototype.solution;

public class Person implements Animal{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Person is created");
    }

    @Override
    public Animal clone(){
        System.out.println("Creating Person...");
        Person person = null;
        try {
            person = (Person) super.clone();


        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    @Override
    public String toString(){
        return "Person: " + name;
    }
}
