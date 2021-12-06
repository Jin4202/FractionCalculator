package com.example.fractioncalculator;

import com.example.fractioncalculator.parser.Parser;
import com.example.fractioncalculator.scanner.Scanner;
import com.example.fractioncalculator.scanner.Token;

public class Calculator {
    public Token[] equation;
    public Calculator(String eq) {
        Scanner sc = new Scanner(eq);
        Parser p = new Parser(sc.getScannedEq());
    }

}
