package com.calculator.fractioncalculator.calculation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class Calculator {

    private String inputString;
    private char currentChar;
    private int inputIndex;

    public Calculator() {}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Literal calculate(String input) throws WrongInputException, ParenthesisNotMatchingException, ZeroDivisionException {
        inputString = input;
        inputIndex = 0;
        currentChar = input.charAt(inputIndex);
        return createTree(input).getOutput();
    }

    private boolean update() {
        inputIndex++;
        if(inputIndex < inputString.length()) {
            currentChar = inputString.charAt(inputIndex);
            return true;
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Calculatetable createTree(String input) throws WrongInputException, ParenthesisNotMatchingException, ZeroDivisionException {
        ArrayList<Element> tokens = new ArrayList<>();
        while(inputIndex < input.length()) {
            //Number
            if('0' <= currentChar && currentChar <= '9' || currentChar == '.') {
                int decimalLoc = -1;
                StringBuilder token = new StringBuilder();
                boolean point = currentChar == '.';
                if(point) {
                    decimalLoc = token.length();
                }
                token.append(currentChar);
                if(update()) {
                    while('0' <= currentChar && currentChar <= '9' || currentChar == '.' && !point) {
                        if(currentChar == '.') {
                            point = true;
                            decimalLoc = token.length();
                        }
                        token.append(currentChar);
                        if(!update()) {
                            break;
                        }
                    }
                } else {
                    // end of input
                    if(point) { // incomplete input
                        throw new WrongInputException();
                    }
                }
                Literal l;
                if(point) {
                    //Fraction
                    double d = Double.parseDouble(token.toString());
                    int n2 = (int)Math.pow(10, token.length()-1-decimalLoc);
                    int n1 = (int)(d*n2);
                    l = new Literal(new Lit(n1), new Lit(n2));
                } else {
                    //Integer
                    int n = Integer.parseInt(token.toString());
                    l = new Literal(new Lit(n), new Lit(1));
                }
                tokens.add(l);
                //End of number parsing
                continue;
            }
            //Operator
            if(currentChar =='+') {
                Plus op = new Plus();
                tokens.add(op);
                update();
                //End of operator parsing
                continue;
            } else if(currentChar == '-') {
                Minus op = new Minus();
                tokens.add(op);
                update();
                //End of operator parsing
                continue;
            } else if(currentChar == '*') {
                Multiply op = new Multiply();
                tokens.add(op);
                update();
                //End of operator parsing
                continue;
            } else if(currentChar == '/') {
                Division op = new Division();
                tokens.add(op);
                update();
                //End of operator parsing
                continue;
            }
            // Constants
            if(currentChar == 'p') {
                Literal l = new Literal(new Lit('p', 1), new Lit(1));
                tokens.add(l);
                update();
                //End of constant parsing
                continue;
            } else if (currentChar == 'e') {
                Literal l = new Literal(new Lit('e', 1), new Lit(1));
                tokens.add(l);
                update();
                //End of constant parsing
                continue;
            }
            //Parenthesis
            if(currentChar == '(') {
                if(!update()) {
                    throw new ParenthesisNotMatchingException();
                }
                StringBuilder subtokens = new StringBuilder();
                int depth = 0;
                while(!(currentChar == ')' && depth == 0)) {
                    if(currentChar == ')') {
                        depth--;
                        if(depth < 0) {
                            throw new ParenthesisNotMatchingException();
                        }
                    }
                    if(currentChar == '(') {
                        depth++;
                    }
                    subtokens.append(currentChar);
                    if(!update()) { // end of input / Error incomplete input
                        throw new ParenthesisNotMatchingException();
                    }
                }
                update();
                if(subtokens.length() == 0) {
                    throw new WrongInputException("There is no element in parenthesis");
                }
                Calculator t = new Calculator();
                Literal l = t.calculate(subtokens.toString());
                tokens.add(l);
            } else if(currentChar == ')') { //Paren is not matching / Error
                throw new ParenthesisNotMatchingException();
            }
        }

        //Start calculation
        // search * & /
        for(int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i) instanceof Multiply || tokens.get(i) instanceof Division) {
                Expression expr = new Expression((Calculatetable)tokens.get(i-1), (Operator)tokens.get(i), (Calculatetable)tokens.get(i+1));
                tokens.remove(i-1);
                tokens.remove(i-1);
                tokens.remove(i-1);
                tokens.add(i-1, expr);
                i--;
            }
        }
        // search + & -
        for(int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i) instanceof Plus || tokens.get(i) instanceof Minus) {
                Expression expr = new Expression((Calculatetable)tokens.get(i-1), (Operator)tokens.get(i), (Calculatetable)tokens.get(i+1));
                tokens.remove(i-1);
                tokens.remove(i-1);
                tokens.remove(i-1);
                tokens.add(i-1, expr);
                i--;
            }
        }
        if(tokens.size() == 1) {
            return (Calculatetable) tokens.get(0);
        } else { //Something went wrong.
            throw new WrongInputException("Left token: "+tokens.size());
        }
    }
}
