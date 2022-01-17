package com.calculator.fractioncalculator.calculation;

import java.util.ArrayList;

public class Calculator {

    private ArrayList<Element> elements;

    public Calculator(String input) {
        elements = tokenizeInput(input);
    }

    private ArrayList<Element> tokenizeInput(String input) {
        ArrayList<Element> tokens = new ArrayList<>();
        int i = 0;
        while(input.length() <= i) {
            char c = input.charAt(i);
            //Number
            if('0' <= c && c <= '9' || c == '.') {
                int decimalLoc = -1;
                StringBuilder token = new StringBuilder();
                boolean point = c == '.';
                if(point) {
                    decimalLoc = token.length();
                }
                token.append(c);
                i++;
                if(input.length() <= i) {
                    break;
                }
                c = input.charAt(i);
                while('0' <= c && c <= '9' || c == '.' && !point) {
                    if (c == '.') {
                        point = true;
                        decimalLoc = token.length();
                    }
                    token.append(c);
                    i++;
                    if (input.length() <= i) {
                        break;
                    }
                    c = input.charAt(i);
                }
                Literal l;
                if(point) {
                    double d = Double.parseDouble(token.toString());
                    int n2 = (int)Math.pow(10, token.length()-decimalLoc);
                    int n1 = (int)d*n2;
                    l = new Literal(n1, n2, 0, 0, 0, 0);
                } else {
                    int n = Integer.parseInt(token.toString());
                    l = new Literal(n, 1, 0, 0, 0, 0);
                }
                tokens.add(l);
            }
            //Operator
            if(c =='+') {
                Operator op = new Plus();
                tokens.add(op);
                i++;
            } else if(c == '-') {
                Operator op = new Minus();
                tokens.add(op);
                i++;
            } else if(c == '*') {
                Operator op = new Multiply();
                tokens.add(op);
                i++;
            } else if(c == '/') {
                Operator op = new Division();
                tokens.add(op);
                i++;
            }
            //Parenthesis
            if(c == '(') {

            } else if(c == ')') {

            }
        }
        return tokens;
    }

    private Expression createExprTree(ArrayList<Element> input) {
        return null;
    }

    public Literal getAnswer() {
        Expression expr = createExprTree(elements);
        Literal answer = expr.getOutput();
        return answer;
    }
}
