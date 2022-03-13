package com.calculator.fractioncalculator.calculation;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class Division extends Operator {
    @Override
    public Literal calculate(Literal n1, Literal n2) throws ZeroDivisionException {
        boolean isZero = true;
        for(int piKey : n2.getNumerator().getCoefficients().keySet()) {
            for(int eKey : n2.getNumerator().getCoefficients().get(piKey).keySet()) {
                if(n2.getNumerator().getCoefficients().get(piKey).get(eKey) != 0) {
                    isZero = false;
                }
            }
        }
        if(isZero) {
            throw new ZeroDivisionException();
        }
        return new Literal(n1.getNumerator().times(n2.getDenominator()), n1.getDenominator().times(n2.getNumerator()));
    }
}
