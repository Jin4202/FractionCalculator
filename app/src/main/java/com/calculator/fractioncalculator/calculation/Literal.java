package com.calculator.fractioncalculator.calculation;

public class Literal {
    private int numerator;
    private int denominator;
    private int eNumerator;
    private int eDenominator;
    private int pNumerator;
    private int pDenominator;

    public Literal(int n1, int n2, int e1, int e2, int p1, int p2) {
        numerator = n1;
        denominator = n2;
        eNumerator = e1;
        eDenominator = e2;
        pNumerator = p1;
        pDenominator = p2;
    }

    private void reduce() {

    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public int geteDenominator() {
        return eDenominator;
    }

    public int geteNumerator() {
        return eNumerator;
    }

    public int getpDenominator() {
        return pDenominator;
    }

    public int getpNumerator() {
        return pNumerator;
    }
}
