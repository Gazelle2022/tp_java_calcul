package com.example;

public class Calculator {

    // Variable inutilisée (code smell)
    private int unusedField = 42;

    public int add(int a, int b) {
        int unusedVariable = 0; // variable inutilisée
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    // Méthode inutilisée (code mort)
    public int subtract(int a, int b) {
        return a - b;
    }

    // Duplication volontaire
    public int duplicateMethod1(int x, int y) {
        return x + y;
    }

    public int duplicateMethod2(int x, int y) {
        return x + y;
    }
//lo
    // Complexité inutile
    public int badLogic(int a) {
        if (a > 0) {
            if (a > 1) {
                if (a > 2) {
                    return a;
                }
            }
        }
        return 0;
    }
}
