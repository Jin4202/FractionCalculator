package com.calculator.fractioncalculator;

public class InputChecker {
    private StringBuilder input;
    private int depth;
    public InputChecker() {
        input = new StringBuilder();
        depth = 0;
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
            }
            if (i == 0 || input.charAt(i-1) == '+' || input.charAt(i-1) == '-' || input.charAt(i-1) == '*' || input.charAt(i-1) == '/') {
                input.insert(i, sign);
            } else {
                i--;
            }
        } while(i > 0);
        return sign.length();
    }

    public int insertConstant(char c, int i) {
        String str;
        if (i == 0 || input.charAt(i-1) == '+' || input.charAt(i-1) == '-' || input.charAt(i-1) == '*' || input.charAt(i-1) == '/') {
            str = ""+c;
        } else {
            str = "";
        }
        input.insert(i, str);
        return str.length();
    }

    public void allclear() {
        input = new StringBuilder();
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
        char prev = input.charAt(i-1);
        if(i == 0 || prev == '+' || prev == '-' || prev == '*' || prev == '/' || prev == '(') {
            str = "(";
            depth++;
        } else if(prev == ')' || '0' <= prev && prev <= '9' || prev == 'p' || prev == 'e') {
            if(depth > 0) {
                str = ")";
                depth--;
            } else {
                str = "*(";
                depth++;
            }
        } else {
            str = "";
        }
        input.insert(i, str);
        return str.length();
    }

    public String getInputString() {
        return input.toString();
    }
}
