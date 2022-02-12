package com.calculator.fractioncalculator.calculation;

public class Division extends Operator {
    @Override
    public Literal calculate(Literal n1, Literal n2) throws ZeroDivisionException {
        if(n2.getNumerator() == 0 && n2.geteNumerator() == 0 && n2.getpNumerator() == 0) {
            throw new ZeroDivisionException();
        }
        return new Literal(n1.getNumerator()*n2.getDenominator(), n1.getDenominator()*n2.getNumerator(),
                n1.geteNumerator()*n2.geteDenominator(), n1.geteDenominator() * n2.geteNumerator(),
                n1.getpNumerator()*n2.getpDenominator(), n1.getpDenominator() * n2.getpNumerator());
    }
}
