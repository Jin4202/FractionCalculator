package com.calculator.fractioncalculator.calculation;

public class Expression {
    private Literal literal;
    private Expression expr1;
    private Operator op;
    private Expression expr2;

    public Expression(Literal n1) {
        this.literal = n1;
        expr1 = null;
        op = null;
        expr2 = null;
    }

    public Expression(Expression n1, Operator op, Expression n2) {
        literal = null;
        this.expr1 = n1;
        this.op = op;
        this.expr2 = n2;
    }

    public Literal getOutput() {
        return literal != null ? literal : op.calculate(expr1.getOutput(), expr2.getOutput());
    }
}
