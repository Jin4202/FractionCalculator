package com.calculator.fractioncalculator.calculation;

import java.util.HashMap;

public class Lit {

    private HashMap<Integer, HashMap<Integer, Integer>> coefficients;

    public Lit() {
        coefficients = new HashMap<>();
    }
    public Lit(int constant) {
        HashMap<Integer, HashMap<Integer, Integer>> c = new HashMap<>();
        c.put(0, new HashMap<>()).put(0, constant);
        coefficients = c;
    }
    public Lit(HashMap<Integer, HashMap<Integer, Integer>> n) {
        coefficients = n;
    }

    public HashMap<Integer, HashMap<Integer, Integer>> getCoefficients() {
        return coefficients;
    }
}
