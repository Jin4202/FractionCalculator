package com.calculator.fractioncalculator.calculation;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class Plus extends Operator {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Literal calculate(Literal n1, Literal n2) {
        return new Literal(n1.getNumerator().times(n2.getDenominator()).add(n2.getNumerator().times(n1.getDenominator())), n1.getDenominator().times(n2.getDenominator()));
    }
}
