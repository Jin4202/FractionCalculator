package com.calculator.fractioncalculator.calculation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Literal implements Element, Calculatetable {
    private Lit numerator;
    private Lit denominator;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Literal(Lit num, Lit denom) {
        numerator = num;
        denominator = denom;
        reduce();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void reduce() {
        //Check coefficient
        int numCommon = 0;
        int denomCommon = 0;
        for(int numPiKey : numerator.getCoefficients().keySet()) {
            for (int numEKey : numerator.getCoefficients().get(numPiKey).keySet()) {
                int coef = numerator.getCoefficients().get(numPiKey).get(numEKey);
                numCommon = (numCommon > 0) ? gcd(numCommon, coef) : coef;
            }
        }
        for(int denomPiKey : denominator.getCoefficients().keySet()) {
            for (int denomEKey : denominator.getCoefficients().get(denomPiKey).keySet()) {
                int coef = denominator.getCoefficients().get(denomPiKey).get(denomEKey);
                denomCommon = (denomCommon > 0) ? gcd(denomCommon, coef) : coef;
            }
        }
        if(numCommon > 0 && denomCommon > 0) {
            int c = gcd(numCommon, denomCommon);
            for(int piKey : numerator.getCoefficients().keySet()) {
                for (int eKey : numerator.getCoefficients().get(piKey).keySet()) {
                    numerator.getCoefficients().get(piKey).put(eKey, numerator.getCoefficients().get(piKey).get(eKey) / c);
                }
            }
            for(int piKey : denominator.getCoefficients().keySet()) {
                for (int eKey : denominator.getCoefficients().get(piKey).keySet()) {
                    denominator.getCoefficients().get(piKey).put(eKey, denominator.getCoefficients().get(piKey).get(eKey) / c);
                }
            }
        }
        //PI
        int numMinPiPow = (int) numerator.getCoefficients().keySet().toArray()[0];
        int denomMinPiPow = (int) denominator.getCoefficients().keySet().toArray()[0];
        int minPiPow = Math.min(numMinPiPow, denomMinPiPow);
        if(minPiPow > 0) {
            HashMap<Integer, HashMap<Integer, Integer>> factoredNum = new HashMap<>();
            for(int piKey : numerator.getCoefficients().keySet()) {
                for(int eKey : numerator.getCoefficients().get(piKey).keySet()) {
                    factoredNum.put(piKey-minPiPow, new HashMap<>());
                    factoredNum.get(piKey-minPiPow).put(eKey, numerator.getCoefficients().get(piKey).get(eKey));
                }
            }
            numerator.setCoefficients(factoredNum);
            HashMap<Integer, HashMap<Integer, Integer>> factoredDenom = new HashMap<>();
            for(int piKey : denominator.getCoefficients().keySet()) {
                for(int eKey : denominator.getCoefficients().get(piKey).keySet()) {
                    factoredDenom.put(piKey-minPiPow, new HashMap<>());
                    factoredDenom.get(piKey-minPiPow).put(eKey, denominator.getCoefficients().get(piKey).get(eKey));
                }
            }
            denominator.setCoefficients(factoredDenom);
        }
        //E
        int numMinEPow = Integer.MAX_VALUE;
        for(int piKey : numerator.getCoefficients().keySet()) {
            for(int eKey : numerator.getCoefficients().get(piKey).keySet()) {
                numMinEPow = Math.min(numMinEPow, eKey);
            }
        }
        int denomMinEPow = Integer.MAX_VALUE;;
        for(int piKey : denominator.getCoefficients().keySet()) {
            for(int eKey : denominator.getCoefficients().get(piKey).keySet()) {
                denomMinEPow = Math.min(denomMinEPow, eKey);
            }
        }
        int minEPow = Math.min(numMinEPow, denomMinEPow);
        if(minEPow > 0) {
            HashMap<Integer, HashMap<Integer, Integer>> factoredNum = new HashMap<>();
            for(int piKey : numerator.getCoefficients().keySet()) {
                for(int eKey : numerator.getCoefficients().get(piKey).keySet()) {
                    factoredNum.put(piKey, new HashMap<>());
                    factoredNum.get(piKey).put(eKey-minEPow, numerator.getCoefficients().get(piKey).get(eKey));
                }
            }
            numerator.setCoefficients(factoredNum);
            HashMap<Integer, HashMap<Integer, Integer>> factoredDenom = new HashMap<>();
            for(int piKey : denominator.getCoefficients().keySet()) {
                for(int eKey : denominator.getCoefficients().get(piKey).keySet()) {
                    factoredDenom.putIfAbsent(piKey, new HashMap<>());
                    factoredDenom.get(piKey).put(eKey-minEPow, denominator.getCoefficients().get(piKey).get(eKey));
                }
            }
            denominator.setCoefficients(factoredDenom);
        }
    }

    private int gcd(int i, int j) {
        while(j > 0) {
            if(i < j) {
                int tmp = i;
                i = j;
                j = tmp;
            } else {
                int tmp = j;
                j = i%j;
                i = tmp;
            }
        }
        return i;
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
        return numerator.getStringOutput() + " / " + denominator.getStringOutput();
    }
}
