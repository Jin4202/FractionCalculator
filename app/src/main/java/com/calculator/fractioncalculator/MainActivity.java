package com.calculator.fractioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.calculator.fractioncalculator.calculation.Calculator;
import com.calculator.fractioncalculator.calculation.ParenthesesNotMatchingException;
import com.calculator.fractioncalculator.calculation.WrongInputException;
import com.calculator.fractioncalculator.calculation.ZeroDivisionException;

public class MainActivity extends AppCompatActivity {

    private TextView textView_input;
    private TextView textView_answer;

    private InputChecker input;
    private int inputIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calculator calculator = new Calculator();

        textView_input = findViewById(R.id.text_equation);
        textView_answer = findViewById(R.id.text_answer);

        input = new InputChecker();
        inputIndex = 0;

        //Inputs
        findViewById(R.id.button_num0).setOnClickListener(view -> {
            inputIndex += input.insertNumber('0', inputIndex);
            refresh();
        });
        findViewById(R.id.button_num1).setOnClickListener(view -> {
            inputIndex += input.insertNumber('1', inputIndex);
            refresh();
        });
        findViewById(R.id.button_num2).setOnClickListener(view -> {
            inputIndex += input.insertNumber('2', inputIndex);
            refresh();
        });
        findViewById(R.id.button_num3).setOnClickListener(view -> {
            inputIndex += input.insertNumber('3', inputIndex);
            refresh();
        });
        findViewById(R.id.button_num4).setOnClickListener(view -> {
            inputIndex += input.insertNumber('4', inputIndex);
            refresh();
        });
        findViewById(R.id.button_num5).setOnClickListener(view -> {
            inputIndex += input.insertNumber('5', inputIndex);
            refresh();
        });
        findViewById(R.id.button_num6).setOnClickListener(view -> {
            inputIndex += input.insertNumber('6', inputIndex);
            refresh();
        });
        findViewById(R.id.button_num7).setOnClickListener(view -> {
            inputIndex += input.insertNumber('7', inputIndex);
            refresh();
        });
        findViewById(R.id.button_num8).setOnClickListener(view -> {
            inputIndex += input.insertNumber('8', inputIndex);
            refresh();
        });
        findViewById(R.id.button_num9).setOnClickListener(view -> {
            inputIndex += input.insertNumber('9', inputIndex);
            refresh();
        });
        findViewById(R.id.button_point).setOnClickListener(view -> {
            inputIndex += input.insertPoint(inputIndex);
            refresh();
        });
        findViewById(R.id.button_plus).setOnClickListener(view -> {
            inputIndex += input.insertOperator('+', inputIndex);
            refresh();
        });
        findViewById(R.id.button_minus).setOnClickListener(view -> {
            inputIndex += input.insertOperator('-', inputIndex);
            refresh();
        });
        findViewById(R.id.button_multiply).setOnClickListener(view -> {
            inputIndex += input.insertOperator('*', inputIndex);
            refresh();
        });
        findViewById(R.id.button_division).setOnClickListener(view -> {
            inputIndex += input.insertOperator('/', inputIndex);
            refresh();
        });
        findViewById(R.id.button_opening_paren).setOnClickListener(view -> {
            inputIndex += input.insertParenthesis(inputIndex);
            refresh();
        });
        findViewById(R.id.button_closing_paren).setOnClickListener(view -> {
            inputIndex += input.insertParenthesis(inputIndex);
            refresh();
        });
        findViewById(R.id.button_PI).setOnClickListener(view -> {
            inputIndex += input.insertConstant('p', inputIndex);
            refresh();
        });
        findViewById(R.id.button_E).setOnClickListener(view -> {
            inputIndex += input.insertConstant('e', inputIndex);
            refresh();
        });
        findViewById(R.id.button_sign).setOnClickListener(view -> {
            inputIndex += input.insertSign(inputIndex);
            refresh();
        });


        //Functions
        findViewById(R.id.button_allclear).setOnClickListener(view -> {
            input = new InputChecker();
            inputIndex = 0;
            refresh();
        });
        findViewById(R.id.button_clear).setOnClickListener(view -> {
            inputIndex += input.clear(inputIndex);
            refresh();
        });
        findViewById(R.id.button_equal).setOnClickListener(view -> {
            try {
                textView_answer.setText(calculator.calculate(input.toString()).getStringOutput());
            } catch (WrongInputException e) {
                //Invalid format
                Toast.makeText(this, "Invalid format.", Toast.LENGTH_SHORT).show();
            } catch (ParenthesesNotMatchingException e) {
                //Parentheses are not matching
                Toast.makeText(this, "Parentheses are not matching.", Toast.LENGTH_SHORT).show();
            } catch (ZeroDivisionException e) {
                //Zero division
                Toast.makeText(this, "Can not divide by zero.", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.button_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findViewById(R.id.button_formatter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    //π
    private void refresh() {
        textView_input.setText(input.toString());
    }
    private String[] getFormattedAnswer(String ans) {
        return new String[] {ans.substring(0, ans.indexOf('/')), ans.substring(ans.indexOf('/')+1)};
    }
}