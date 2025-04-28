package io.dowlath.designpattern.creational.factory;

public class ShapeFactory {

    public Shape getShape(String input){
        switch(input){
            case "CIRCLE":
                return new Circle();
            case "RECTANGLE":
                return new Rectangle();
            case "SQUARE":
                return new Square();
            case default:
                return null;
        }

    }
}
