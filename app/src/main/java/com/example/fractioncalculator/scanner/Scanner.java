package com.example.fractioncalculator.scanner;

import java.util.ArrayList;

public class Scanner {
    private final String input;
    private int i;
    private char currentChar;
    private Token token;
    private final ArrayList<Token> equation;

    public Scanner(String input) {
        this.input = input;
        i = 0;
        currentChar = input.charAt(i);
        token = new Token();
        equation = new ArrayList<>();
        scanInput();
    }

    public Token[] getScannedEq () {
        Token[] output = new Token[equation.size()];
        for(int i = 0; i < equation.size(); i++) {
            output[i] = equation.get(i);
        }
        return output;
    }

    public void scanInput() {
        while (i < input.length()) {
            scan();
        }
    }

    public boolean accept() {
        token.addLex(currentChar);
        i++;
        if(i < input.length()) {
            currentChar = input.charAt(i);
            return true;
        } else {
            tokenize();
            return false;
        }
    }

    public void tokenize() {
        equation.add(token);
        token = new Token();
    }


    public void scan() {
        switch(currentChar) {
            case '.':
            case '0':            case '1':
            case '2':            case '3':
            case '4':            case '5':
            case '6':            case '7':
            case '8':            case '9':
                token.kind = Token.INTLITERAL;
                while('0' <= currentChar && currentChar <= '9' || currentChar == '.') {
                    if(currentChar == '.' && token.kind == Token.INTLITERAL) {
                        token.kind = Token.FLOATLITERAL;
                    } else {
                        tokenize();
                        break;
                    }
                    if(!accept()) return;
                }
                tokenize();
                break;
            case '+':
                token.kind = Token.PLUS;
                if(!accept()) return;
                tokenize();
                break;
            case '-':
                token.kind = Token.MINUS;
                if(!accept()) return;
                tokenize();
                break;
            case '*':
                token.kind = Token.TIMES;
                if(!accept()) return;
                tokenize();
                break;
            case '/':
                token.kind = Token.DIV;
                if(!accept()) return;
                tokenize();
                break;
            case '(':
                token.kind = Token.LEFTPAREN;
                if(!accept()) return;
                tokenize();
                break;
            case ')':
                token.kind = Token.RIGHTPAREN;
                if(!accept()) return;
                tokenize();
                break;
        }
    }
}
