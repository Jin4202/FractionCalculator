package com.calculator.fractioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.calculator.fractioncalculator.calculation.Calculator;
import com.calculator.fractioncalculator.calculation.Literal;
import com.calculator.fractioncalculator.calculation.ParenthesesNotMatchingException;
import com.calculator.fractioncalculator.calculation.WrongInputException;
import com.calculator.fractioncalculator.calculation.ZeroDivisionException;

public class MainActivity extends AppCompatActivity {

    private TextView textView_input;
    private TextView textView_answerNumerator;
    private View view_answerLineBreaker;
    private TextView textView_answerDenominator;

    private InputChecker input;
    private int inputIndex;

    private boolean answerFormatFraction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calculator calculator = new Calculator();

        textView_input = findViewById(R.id.text_equation);
        textView_input.setText("");


        textView_answerNumerator = findViewById(R.id.text_answerNumerator);
        view_answerLineBreaker = findViewById(R.id.lineBreaker);
        textView_answerDenominator = findViewById(R.id.text_answerDenominator);
        textView_answerNumerator.setText("1");
        view_answerLineBreaker.setVisibility(View.VISIBLE);
        textView_answerDenominator.setText("1");

        input = new InputChecker();
        inputIndex = 0;

        answerFormatFraction = true;

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
        findViewById(R.id.button_answer).setOnClickListener(view -> {
            inputIndex += input.insertConstant('A', inputIndex);
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
        Button button_equal = findViewById(R.id.button_equal);
        button_equal.setOnClickListener(view -> {
            try {
                if(input.toString().length() > 0) {
                    Literal answer = calculator.calculate(input.toString());
                    String numerator = answer.getNumerator().getStringOutput();
                    String denominator = answer.getDenominator().getStringOutput();
                    if(answerFormatFraction) {
                        if(getRealValue(numerator) == 0) {
                            textView_answerNumerator.setText(String.valueOf(0));
                            view_answerLineBreaker.setVisibility(View.VISIBLE);
                            textView_answerDenominator.setText(String.valueOf(1));
                        } else {
                            textView_answerNumerator.setText(formatAnswer(numerator));
                            textView_answerDenominator.setText(formatAnswer(denominator));
                            view_answerLineBreaker.setVisibility(View.VISIBLE);


                            Log.d("num", "text: " + textView_answerNumerator.getText().toString());
                            Log.d("num", "len: " + textView_answerNumerator.length());
                            Log.d("denom", "text: " + textView_answerDenominator.getText().toString());
                            Log.d("denom", "len: " + textView_answerDenominator.length());
                            /*
                            if(textView_answerNumerator.length() > textView_answerDenominator.length()) {
                                textView_answerDenominator.setEms(textView_answerNumerator.length());
                            } else {
                                textView_answerNumerator.setEms(textView_answerDenominator.length());
                            }
                            */
                        }
                    } else {
                        textView_answerNumerator.setText(String.format("%s", getRealValue(numerator) / getRealValue(denominator)));
                        view_answerLineBreaker.setVisibility(View.INVISIBLE);
                        textView_answerDenominator.setText("");
                    }
                }
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
        findViewById(R.id.button_formatter).setOnClickListener(view -> {
            answerFormatFraction = !answerFormatFraction;
            button_equal.performClick();
        });
    }

    //translate into user friendly format
    private SpannableStringBuilder formatAnswer(String answer) {
        //Log.d("input", answer);
        SpannableStringBuilder outputAnswer = new SpannableStringBuilder();
        StringBuilder answerText = new StringBuilder(answer);
        while(answerText.indexOf("(") != -1) {
            // var = "+c(p,e)"
            StringBuilder var = new StringBuilder(answerText.substring(0, answerText.indexOf(")")+1));
            answerText.delete(0, answerText.indexOf(")")+1);
            //Sign
            boolean isNegative = false;
            if(var.charAt(0) == '-') {
                isNegative = true;
            }
            if(var.charAt(0) == '+' || isNegative) {
                var.deleteCharAt(0);
            }
            //c
            int c = Integer.parseInt(var.substring(0, var.indexOf("(")));
            //(p,e)
            int piPower = Integer.parseInt(var.substring(var.indexOf("(")+1, var.indexOf(",")));
            int ePower = Integer.parseInt(var.substring(var.indexOf(",")+1, var.indexOf(")")));
            SpannableStringBuilder pi = new SpannableStringBuilder();
            if(piPower > 0) {
                pi.append("π");
                pi.append(String.valueOf(piPower));
                pi.setSpan(new SuperscriptSpan(), 1, pi.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                pi.setSpan(new RelativeSizeSpan(0.75f), 1, pi.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            SpannableStringBuilder e = new SpannableStringBuilder();
            if(ePower > 0) {
                e.append("e");
                e.append(String.valueOf(ePower));
                e.setSpan(new SuperscriptSpan(), 1, e.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                e.setSpan(new RelativeSizeSpan(0.75f), 1, e.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if(isNegative) {
                outputAnswer.append("-");
            } else {
                if(outputAnswer.length() > 0) {
                    outputAnswer.append("+");
                }
            }
            if(c > 1 || c == 1 && piPower == 0 && ePower == 0) {
                outputAnswer.append(String.valueOf(c));
            }
            outputAnswer.append(pi).append(e);
        }
        //Log.d("output", outputAnswer.toString());
        return outputAnswer;
    }

    private double getRealValue(String answer) {
        StringBuilder answerText = new StringBuilder(answer);
        double result = 0d;
        while(answerText.indexOf(")") != -1) {
            int i = answerText.indexOf(")");
            String var = answerText.substring(0, i+1);
            answerText.delete(0, i+1);
            if(answerText.length() > 0 && answerText.charAt(0) == '+') {
                answerText.delete(0, 1);
            }

            int n = Integer.parseInt(var.substring(0, var.indexOf('(')));
            int piPower = Integer.parseInt(var.substring(var.indexOf('(')+1, var.indexOf(',')));
            int ePower = Integer.parseInt(var.substring(var.indexOf(',')+1, var.indexOf(')')));

            result += n * Math.pow(Math.PI, piPower) * Math.pow(Math.E, ePower);
        }
        return result;
    }
    private void refresh() {
        textView_input.setText(input.getDisplayString());
    }
}