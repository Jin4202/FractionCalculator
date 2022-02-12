package com.calculator.fractioncalculator.calculation;

public class ParenthesisNotMatchingException extends  Exception {
    public ParenthesisNotMatchingException() {
        super("Parenthesis not matching");
    }
}
