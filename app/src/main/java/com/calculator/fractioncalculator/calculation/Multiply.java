package com.calculator.fractioncalculator.calculation;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class Multiply extends Operator {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Literal calculate(Literal n1, Literal n2) {
        return new Literal(n1.getNumerator().times(n2.getNumerator()), n1.getDenominator().times(n2.getDenominator()));
    }
}
