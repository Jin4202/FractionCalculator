package com.calculator.fractioncalculator.calculation;

public abstract class Operator implements Element {
    public abstract Literal calculate(Literal n1, Literal n2);
}
