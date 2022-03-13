package com.calculator.fractioncalculator;

public class InputChecker {
    private static final char[] CONSTANTS = new char[] {'p', 'e', 'A'};
    private StringBuilder input;
    public InputChecker() {
        input = new StringBuilder();
    }

    public int insertNumber(char c, int i) {
        String str;
        if(i > 0 && input.charAt(i-1) == ')') {
            str = "*"+c;
        } else {
            str = ""+c;
        }
        input.insert(i, str);
        return str.length();
    }

    private boolean isConstant(char c) {
        for(char constant : CONSTANTS) {
            if(c == constant) {
                return true;
            }
        }
        return false;
    }

    public int insertPoint(int i) {
        String str;
        if(i == 0 || input.charAt(i-1) < '0' || '9' < input.charAt(i-1)) {
            if(i > 0 && input.charAt(i-1) == ')') {
                str = "*0.";
            } else {
                str ="0.";
            }
        } else {
            str = ".";
        }
        input.insert(i, str);
        return str.length();
    }

    public int insertOperator(char c, int i) {
        String str;
        if (i == 0 || input.charAt(i-1) == '+' || input.charAt(i-1) == '-' || input.charAt(i-1) == '*' || input.charAt(i-1) == '/') {
            str = "";
        } else {
            str = ""+c;
        }
        input.insert(i, str);
        return str.length();
    }

    public int insertSign(int i) {
        String sign = "(-";
        do {
            if(i >= 2 && input.charAt(i-2) == '(' && input.charAt(i-1) == '-') {
                input.delete(i-2, i);
                return -sign.length();
            } else if (i == 0 || input.charAt(i-1) == '+' || input.charAt(i-1) == '-' || input.charAt(i-1) == '*' || input.charAt(i-1) == '/') {
                input.insert(i, sign);
                break;
            } else {
                i--;
            }
        } while(i >= 0);
        return sign.length();
    }

    public int insertConstant(char c, int i) {
        String str;
        char prev;
        if (i == 0 || (prev = input.charAt(i-1)) == '+' || prev == '-' || prev == '*' || prev == '/' || prev == '(') {
            str = ""+c;
        } else {
            str = "*"+c;
        }
        input.insert(i, str);
        return str.length();
    }

    public int clear(int i) {
        if(i > 0) {
            input.delete(i-1,i);
            return -1;
        }
        return 0;
    }

    public int insertParenthesis(int i) {
        String str;
        char prev;
        if(i == 0 || (prev = input.charAt(i-1)) == '+' || prev == '-' || prev == '*' || prev == '/' || prev == '(') {
            str = "(";
        } else if(prev == ')' || '0' <= prev && prev <= '9' || isConstant(prev)) {
            int count = 0;
            for(int j = 0; j < input.length(); j++) {
                if(input.charAt(j) == '(') {
                    count++;
                } else if(input.charAt(j) == ')') {
                    count--;
                }
            }
            if(count >= 1) {
                str = ")";
            } else {
                str = "*(";
            }
        } else {
            str = "";
        }
        input.insert(i, str);
        return str.length();
    }

    @Override
    public String toString() {
        return input.toString();
    }

    public String getDisplayString() {
        StringBuilder output = new StringBuilder(input);
        while(output.indexOf("p") != -1) {
            int i = output.indexOf("p");
            output.replace(i, i+1, "π");
        }
        if(output.indexOf("A") != -1) {
            int i = output.indexOf("p");
            output.replace(i, i+1, "Ans");
        }
        return output.toString();
    }
}
