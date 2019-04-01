package br.edu.utfpr.cp.lesson07;

import java.util.stream.Stream;

abstract class Shape {
    abstract void print();
}

class Square extends Shape {
    void print() {
        System.out.println("Square");
    }
}

class Circle extends Shape {
    void print() {
        System.out.println("Circle");
    }
}

class Diamond extends Shape {
    void print() {
        System.out.println("Diamond");
    }
}

public class Main {
    public static void main(String[] args) {
        Stream.of(new Square(), new Circle(), new Diamond()).forEach(Shape::print);

    }
}