package com.calculator.fractioncalculator.calculation;

public class Division extends Operator {
    @Override
    public Literal calculate(Literal n1, Literal n2) {
        return new Literal(n1.getNumerator()*n2.getDenominator(), n1.getNumerator()*n2.getDenominator(),
                n1.geteNumerator()*n2.geteDenominator(), n1.geteDenominator() * n2.geteNumerator(),
                n1.getpNumerator()*n2.getpDenominator(), n1.getpDenominator() * n2.getpNumerator());
    }
}
