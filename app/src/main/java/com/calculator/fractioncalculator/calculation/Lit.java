package com.calculator.fractioncalculator.calculation;

import java.util.ArrayList;
import java.util.HashMap;

public class Lit {

    private HashMap<Integer, HashMap<Integer, Integer>> coefficients;

    public Lit() {
        coefficients = new HashMap<>();
    }
    public Lit(int constant) {
        HashMap<Integer, HashMap<Integer, Integer>> c = new HashMap<>();
        c.put(0, new HashMap<>());
        c.get(0).put(0, constant);
        coefficients = c;
    }
    public Lit(char constant, int power) {
        HashMap<Integer, HashMap<Integer, Integer>> c = new HashMap<>();
        if(constant == 'p') {
            c.put(power, new HashMap<>());
            c.get(power).put(0, 1);
        } else if(constant == 'e'){
            c.put(0, new HashMap<>());
            c.get(0).put(power, 1);
        }
        coefficients = c;
    }
    public Lit(HashMap<Integer, HashMap<Integer, Integer>> n) {
        coefficients = n;
    }

    public Lit add(Lit n2) {
        Lit output = new Lit();
        for(int piKey : this.getCoefficients().keySet()) {
            for (int eKey : this.getCoefficients().get(piKey).keySet()) {
                int c = this.getCoefficients().get(piKey).get(eKey);
                if(output.getCoefficients().get(piKey) == null || output.getCoefficients().get(piKey).size() == 0) {
                    output.getCoefficients().put(piKey, new HashMap<>());
                }
                output.getCoefficients().get(piKey).put(eKey, c);
            }
        }
        for(int piKey : n2.getCoefficients().keySet()) {
            for (int eKey : n2.getCoefficients().get(piKey).keySet()) {
                int c = n2.getCoefficients().get(piKey).get(eKey);
                if(output.getCoefficients().get(piKey) == null || output.getCoefficients().get(piKey).size() == 0) {
                    output.getCoefficients().put(piKey, new HashMap<>());
                    output.getCoefficients().get(piKey).put(eKey, 0);
                } else if(output.getCoefficients().get(piKey).get(eKey) == null) {
                    output.getCoefficients().get(piKey).put(eKey, 0);
                }
                HashMap<Integer, Integer> a = output.getCoefficients().get(piKey);
                int n1 = output.getCoefficients().get(piKey).get(eKey);
                output.getCoefficients().get(piKey).put(eKey, n1+c);
            }
        }
        return output;
    }

    public Lit times(Lit n2) {
        String a1 = this.getStringOutput();
        String a2 = n2.getStringOutput();
        ArrayList<Lit> lits = new ArrayList<>();
        for(int n2PiKey : n2.getCoefficients().keySet()) {
            for (int n2EKey : n2.getCoefficients().get(n2PiKey).keySet()) {
                int c2 = n2.getCoefficients().get(n2PiKey).get(n2EKey);
                HashMap<Integer, HashMap<Integer, Integer>> m = new HashMap<>();
                for(int n1PiKey : this.getCoefficients().keySet()) {
                    for(int n1EKey : this.getCoefficients().get(n1PiKey).keySet()) {
                        int c1 = this.getCoefficients().get(n1PiKey).get(n1EKey);
                        if(m.get(n1PiKey+n2PiKey) == null || m.get(n1PiKey+n2PiKey).size() == 0) {
                            m.put(n1PiKey+n2PiKey, new HashMap<>());
                        }
                        m.get(n1PiKey+n2PiKey).put(n1EKey+n2EKey, c1*c2);
                    }
                }
                lits.add(new Lit(m));
            }
        }
        Lit output = new Lit();
        for(Lit l : lits) {
            output = output.add(l);
        }
        return output;
    }

    public void setCoefficients(HashMap<Integer, HashMap<Integer, Integer>> coefficients) {
        this.coefficients = coefficients;
    }

    public HashMap<Integer, HashMap<Integer, Integer>> getCoefficients() {
        return coefficients;
    }

    public String getStringOutput() {
        StringBuilder output = new StringBuilder();
        for(int piKey : this.getCoefficients().keySet()) {
            for(int eKey : this.getCoefficients().get(piKey).keySet()) {
                int coef = this.getCoefficients().get(piKey).get(eKey);
                if(output.length() > 0) {
                    if(coef > 0) {
                        output.append("+");
                    }
                }
                output.append(coef+"("+piKey+","+eKey+")");
            }
        }
        return output.toString();
    }
}
