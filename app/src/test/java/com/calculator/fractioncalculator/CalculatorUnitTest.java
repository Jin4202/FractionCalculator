package com.calculator.fractioncalculator;
import com.calculator.fractioncalculator.calculation.Calculator;
import com.calculator.fractioncalculator.calculation.ParenthesisNotMatchingException;
import com.calculator.fractioncalculator.calculation.WrongInputException;
import com.calculator.fractioncalculator.calculation.ZeroDivisionException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculatorUnitTest {
    Calculator calculator = new Calculator();

    @Test
    public void testCalculator() {
        try {
            Assert.assertEquals("(3 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("3").getStringOutput());
        } catch (WrongInputException | ParenthesisNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBasicOperation() {
        try {
            Assert.assertEquals("(1 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("1").getStringOutput());
            //Integer
            Assert.assertEquals("(10 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("5+5").getStringOutput());
            Assert.assertEquals("(3 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("5-2").getStringOutput());
            Assert.assertEquals("(25 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("5*5").getStringOutput());
            Assert.assertEquals("(1 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("5/5").getStringOutput());
            //Float
            Assert.assertEquals("(11 / 10) + (0 / 0)e + (0 / 0)pi", calculator.calculate("0.5+0.6").getStringOutput());
            Assert.assertEquals("(5 / 2) + (0 / 0)e + (0 / 0)pi", calculator.calculate("5-2.5").getStringOutput());
            Assert.assertEquals("(5 / 2) + (0 / 0)e + (0 / 0)pi", calculator.calculate("5.0*0.5").getStringOutput());
            Assert.assertEquals("(5 / 2) + (0 / 0)e + (0 / 0)pi", calculator.calculate("5/2").getStringOutput());
        } catch (WrongInputException | ParenthesisNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExceptionHandling() {
        try {
            calculator.calculate("5/0").getStringOutput();
        } catch (ZeroDivisionException e) {
            Assert.assertTrue(true);
        } catch (WrongInputException | ParenthesisNotMatchingException e) {
            e.printStackTrace();
        }

        try {
            calculator.calculate("3.0/(3*0)").getStringOutput();
        } catch (ZeroDivisionException e) {
            Assert.assertTrue(true);
        } catch (WrongInputException | ParenthesisNotMatchingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFloatInput() {
        try {
            Assert.assertEquals("(3 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("3.0").getStringOutput());
            Assert.assertEquals("(8 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("3.0+5").getStringOutput());
            Assert.assertEquals("(9 / 2) + (0 / 0)e + (0 / 0)pi", calculator.calculate("1.2+3.3").getStringOutput());
        } catch (WrongInputException | ParenthesisNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParenthesis() {
        try {
            Assert.assertEquals("(8 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("5+(1+2)").getStringOutput());
            Assert.assertEquals("(8 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("(3.0+5.0)").getStringOutput());
            Assert.assertEquals("(9 / 1) + (0 / 0)e + (0 / 0)pi", calculator.calculate("2*(1.2+3.3)").getStringOutput());
            Assert.assertEquals("(29 / 2) + (0 / 0)e + (0 / 0)pi", calculator.calculate("((2*5)+(1.2+3.3))").getStringOutput());
        } catch (WrongInputException | ParenthesisNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }

        try {
            calculator.calculate("()").getStringOutput();
        } catch(WrongInputException e) {
            Assert.assertTrue(true);
        } catch (ParenthesisNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }

        try {
            calculator.calculate("(1+1))").getStringOutput();
        } catch(ParenthesisNotMatchingException e) {
            Assert.assertTrue(true);
        } catch (WrongInputException | ZeroDivisionException e) {
            e.printStackTrace();
        }
    }
}