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
        reduce();
    }

    private void reduce() {
        int gcd1 = gcd(Math.abs(numerator), Math.abs(denominator));
        int gcd2 = gcd(Math.abs(eNumerator), Math.abs(eDenominator));
        int gcd3 = gcd(Math.abs(pNumerator), Math.abs(pDenominator));
        numerator /= gcd1;
        denominator /= gcd1;
        eNumerator /= gcd2;
        eDenominator /= gcd2;
        pNumerator /= gcd3;
        pDenominator /= gcd3;
    }

    private int gcd(int i, int j) {
        while(j > 0) {
            if(i < j) {
                int tmp = i;
                i = j;
                j = tmp;
            } else {
                int tmp = j;
                j = i%j;
                i = tmp;
            }
        }
        return i;
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
