package com.calculator.fractioncalculator.calculation;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class Division extends Operator {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Literal calculate(Literal n1, Literal n2) throws ZeroDivisionException {
        boolean isZero = true;
        for(int piKey : n2.getNumerator().getCoefficients().keySet()) {
            if(n2.getNumerator().getCoefficients().get(piKey).size() > 0) {
                isZero = false;
            }
        }
        if(isZero) {
            throw new ZeroDivisionException();
        }
        return new Literal(n1.getNumerator().times(n2.getDenominator()), n1.getDenominator().times(n2.getNumerator()));
    }
}
