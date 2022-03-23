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

import java.util.ArrayList;

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
        textView_answerNumerator.setText("");
        view_answerLineBreaker.setVisibility(View.INVISIBLE);
        textView_answerDenominator.setText("");

        input = new InputChecker();
        inputIndex = 0;

        answerFormatFraction = true;

        //Inputs

        //Numbers
        Button[] numberPad = new Button[10];
        for(int i = 0; i < 10; i++) {
            String id_name = "button_num"+i;
            int id = getResources().getIdentifier(id_name, "id", getPackageName());
            numberPad[i] = findViewById(id);
            int finalI = i;
            numberPad[i].setOnClickListener(view -> {
                inputIndex += input.insertNumber((char)('0'+ finalI), inputIndex);
                refresh();
            });
        }
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
        Button button_sign = findViewById(R.id.button_sign);
        button_sign.setOnClickListener(view -> {
            inputIndex += input.insertSign(inputIndex);
            refresh();
        });

        Button button_ans = findViewById(R.id.button_answer);
        button_ans.setOnClickListener(view -> {
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
                            textView_answerNumerator.measure(0,0);
                            int n_len = textView_answerNumerator.getMeasuredWidth();
                            int d_len = textView_answerDenominator.getMeasuredWidth();
                            view_answerLineBreaker.getLayoutParams().width = Math.max(n_len, d_len);
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
        Button button_formatter = findViewById(R.id.button_formatter);
        button_formatter.setOnClickListener(view -> {
            answerFormatFraction = !answerFormatFraction;
            button_equal.performClick();
        });

        //setting attributes
        ArrayList<TextView> singleLineViews = new ArrayList<>();
        singleLineViews.add(textView_input);
        singleLineViews.add(textView_answerNumerator);
        singleLineViews.add(textView_answerDenominator);
        singleLineViews.add(button_sign);
        singleLineViews.add(button_ans);
        singleLineViews.add(button_formatter);

        for(TextView v : singleLineViews) {
            v.setMaxLines(1);
            v.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        }

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