package io.dowlath.designpattern.creational.factory;

public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shapeObject = shapeFactory.getShape("CIRCLE");
        shapeObject.draw();
    }
}
