package com.calculator.fractioncalculator.calculation;

public class Expression implements Calculatetable {
    private Expression expr1;
    private Operator op;
    private Expression expr2;

    public Expression(Expression n1, Operator op, Expression n2) {
        this.expr1 = n1;
        this.op = op;
        this.expr2 = n2;
    }

    public Literal getOutput() {
        return op.calculate(expr1.getOutput(), expr2.getOutput());
    }
}
