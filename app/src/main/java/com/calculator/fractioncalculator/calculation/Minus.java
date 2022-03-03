package com.calculator.fractioncalculator.calculation;

import android.os.Build;
import android.util.Pair;

import androidx.annotation.RequiresApi;

import java.util.HashMap;

public class Minus extends Operator {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Literal calculate(Literal n1, Literal n2) {
        HashMap<Integer, HashMap<Integer, Pair<Integer, Integer>>> negOne = new HashMap<>();
        negOne.getOrDefault(0, new HashMap<>()).put(0, new Pair<>(-1, 0));
        n2 = new Literal(n2.getNumerator().times(new Lit(negOne)), n2.getDenominator());
        return new Literal(n1.getNumerator().times(n2.getDenominator()).add(n2.getNumerator().times(n1.getDenominator())), n1.getDenominator().times(n2.getDenominator()));
    }
}
