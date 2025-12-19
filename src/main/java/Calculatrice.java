package com.example;

public class Calculatrice {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division par zéro impossible");
        }
        return a / b;
    }

    // Méthode principale pour tester en console
    public static void main(String[] args) {
        Calculatrice calc = new Calculatrice();
        System.out.println("5 + 3 = " + calc.add(5,3));
        System.out.println("5 - 3 = " + calc.subtract(5,3));
        System.out.println("5 * 3 = " + calc.multiply(5,3));
        System.out.println("5 / 3 = " + calc.divide(5,3));
    }
}
