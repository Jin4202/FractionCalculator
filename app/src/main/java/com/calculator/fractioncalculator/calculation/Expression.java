package com.calculator.fractioncalculator.calculation;

public class Expression {
    private Literal n1;
    private Operator op;
    private Literal n2;

    public Expression(Literal n1) {
        this.n1 = n1;
        op = null;
        n2 = null;
    }

    public Expression(Literal n1, Operator op, Literal n2) {
        this.n1 = n1;
        this.op = op;
        this.n2 = n2;
    }

    public Literal getOutput() {
        return op.calculate(n1, n2);
    }
}
