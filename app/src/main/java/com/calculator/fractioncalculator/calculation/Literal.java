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
                int coef = numerator.getCoefficients().get(denomPiKey).get(denomEKey);
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

        //E
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
        return "";
    }
}
