package com.example.fractioncalculator.scanner;

public class Token {
    public int kind;
    private StringBuffer lex;
    public Token() {
        kind = -1;
        lex = new StringBuffer();
    }

    public void addLex(char c) {
        lex.append(c);
    }

    public String getLexeme() {
        return lex.toString();
    }

    public static int
        INTLITERAL = 0,
        FLOATLITERAL = 1,

        PLUS = 2,
        MINUS = 3,
        TIMES = 4,
        DIV = 5,

        LEFTPAREN = 6,
        RIGHTPAREN = 7;
}

