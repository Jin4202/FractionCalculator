package com.calculator.fractioncalculator.calculation;

public class ParenthesesNotMatchingException extends  Exception {
    public ParenthesesNotMatchingException() {
        super("Parenthesis not matching");
    }
}
