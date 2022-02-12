package com.calculator.fractioncalculator.calculation;

public class WrongInputException extends Exception {
    public WrongInputException() {
        super("Wrong input");
    }
    public WrongInputException(String message) {
        super(message);
    }

}
