package com.calculator.fractioncalculator.calculation;

import android.os.Build;
import android.util.Pair;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;

public class Lit {

    private HashMap<Integer, HashMap<Integer, Pair<Integer, Integer>>> coeffients;

    public Lit() {
        coeffients = new HashMap<>();
    }
    public Lit(HashMap<Integer, HashMap<Integer, Pair<Integer, Integer>>> n) {
        coeffients = n;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Lit times(Lit n) {
        ArrayList<Lit> lits = new ArrayList<>();
        for(int m2PiKey : n.coeffients.keySet()) {
            for (int m2EKey : n.coeffients.get(m2PiKey).keySet()) {
                HashMap<Integer, HashMap<Integer, Pair<Integer, Integer>>> m = new HashMap<>();
                for(int m1PiKey : this.coeffients.keySet()) {
                    for(int m1EKey : this.coeffients.get(m1PiKey).keySet()) {
                        Pair<Integer, Integer> m1Pair = this.coeffients.get(m1PiKey).get(m1EKey);
                        Pair<Integer, Integer> m2Pair = n.coeffients.get(m2PiKey).get(m2EKey);
                        int f = m1Pair.first*m2Pair.first;
                        int s = m1Pair.second*m2Pair.second;
                        Pair<Integer, Integer> output = getFactoredPair(f, s);
                        m.getOrDefault(m1PiKey+m2PiKey, new HashMap<>()).put(m1EKey+m2EKey, output);
                    }
                }
                lits.add(new Lit(m));
            }
        }
        Lit output = new Lit();
        for(Lit l : lits) {
            output.add(l);
        }
        return output;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Lit add(Lit n) {
        Lit output = new Lit();
        for(int piKey : n.coeffients.keySet()) {
            for (int eKey : n.coeffients.get(piKey).keySet()) {
                Pair<Integer, Integer> tPair = this.coeffients.getOrDefault(piKey, new HashMap<>()).getOrDefault(eKey, new Pair<>(0,0));
                Pair<Integer, Integer> nPair = n.coeffients.get(piKey).get(eKey);
                Pair<Integer, Integer> result;
                if(nPair.second == 0) {
                    result = tPair;
                } else if(tPair.second == 0) { // 0 +/- n
                    result = nPair;
                } else { // t +/- n
                    int f = tPair.first*nPair.second + nPair.first*tPair.second;
                    int s = tPair.second*nPair.second;
                    result = getFactoredPair(f, s);
                }
                output.coeffients.getOrDefault(piKey, new HashMap<>()).put(eKey, result);
            }
        }
        return output;
    }

    private Pair<Integer, Integer> getFactoredPair(int f, int s) {
        int d = gcd(f, s);
        if(d != 0) {
            f /= d;
            s /= d;
        }
        return new Pair<>(f, s);
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

    public HashMap<Integer, HashMap<Integer, Pair<Integer, Integer>>> getCoefficients() {
        return coeffients;
    }
}
