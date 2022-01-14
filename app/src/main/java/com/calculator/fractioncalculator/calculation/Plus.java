package com.calculator.fractioncalculator.calculation;

public class Plus extends Operator {
    @Override
    public Literal calculate(Literal n1, Literal n2) {
        return new Literal(n1.getNumerator()*n2.getDenominator() + n2.getNumerator()*n1.getDenominator(), n1.getDenominator() * n2.getDenominator(),
                n1.geteNumerator()*n2.geteDenominator() + n2.geteNumerator()*n1.geteDenominator(), n1.geteDenominator() * n2.geteDenominator(),
                n1.getpNumerator()*n2.getpDenominator() + n2.getpNumerator()*n1.getpDenominator(), n1.getpDenominator() * n2.getpDenominator());
    }
}
