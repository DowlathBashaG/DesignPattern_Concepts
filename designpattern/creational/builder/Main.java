package io.dowlath.designpattern.creational.builder;

public class Main{

    public static void main(String args[]){

        User James = new User.UserBuilder("Jame","Bond")
                .address("123,London")
                .age(45)
                .phoneNumber("007")
                .build();

        System.out.println(James);

    }
}