package com.calculator.fractioncalculator.calculation;

import java.util.Locale;

public class Literal implements Element, Calculatetable{
    private Lit numerator;
    private Lit denominator;

    public Literal(Lit num, Lit denom) {
        numerator = num;
        denominator = denom;
        reduce();
    }

    private void reduce() {
        
    }

    public Lit getNumerator() {
        return numerator;
    }

    public Lit getDenominator() {
        return denominator;
    }

    @Override
    public Literal getOutput() {
        return this;
    }

    public String getStringOutput() {
        return "";
    }
}
