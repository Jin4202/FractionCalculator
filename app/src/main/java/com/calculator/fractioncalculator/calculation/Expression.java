package com.calculator.fractioncalculator.calculation;

public class Expression implements Element, Calculatetable {
    private Calculatetable expr1;
    private Operator op;
    private Calculatetable expr2;

    public Expression(Calculatetable n1, Operator op, Calculatetable n2) {
        this.expr1 = n1;
        this.op = op;
        this.expr2 = n2;
    }

    public Literal getOutput() {
        return op.calculate(expr1.getOutput(), expr2.getOutput());
    }
}
