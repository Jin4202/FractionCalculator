package com.calculator.fractioncalculator;
import com.calculator.fractioncalculator.calculation.Calculator;
import com.calculator.fractioncalculator.calculation.ParenthesesNotMatchingException;
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
            Assert.assertEquals("3(0,0) / 1(0,0)", calculator.calculate("3").getStringOutput());
        } catch (WrongInputException | ParenthesesNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBasicOperation() {
        try {
            //Integer
            Assert.assertEquals("10(0,0) / 1(0,0)", calculator.calculate("5+5").getStringOutput());
            Assert.assertEquals("3(0,0) / 1(0,0)", calculator.calculate("5-2").getStringOutput());
            Assert.assertEquals("25(0,0) / 1(0,0)", calculator.calculate("5*5").getStringOutput());
            Assert.assertEquals("1(0,0) / 1(0,0)", calculator.calculate("5/5").getStringOutput());
            Assert.assertEquals("15(0,0) / 1(0,0)", calculator.calculate("5+5+5").getStringOutput());
            //Float
            Assert.assertEquals("11(0,0) / 10(0,0)", calculator.calculate("0.5+0.6").getStringOutput());
            Assert.assertEquals("5(0,0) / 2(0,0)", calculator.calculate("5-2.5").getStringOutput());
            Assert.assertEquals("5(0,0) / 2(0,0)", calculator.calculate("5.0*0.5").getStringOutput());
            Assert.assertEquals("5(0,0) / 2(0,0)", calculator.calculate("5/2").getStringOutput());
            //Zero
            Assert.assertEquals("5(0,0) / 1(0,0)", calculator.calculate("5+0").getStringOutput());
            Assert.assertEquals("5(0,0) / 1(0,0)", calculator.calculate("5-0").getStringOutput());
            Assert.assertEquals("-5(0,0) / 1(0,0)", calculator.calculate("0-5").getStringOutput());
            Assert.assertEquals("0(0,0) / 1(0,0)", calculator.calculate("5*0").getStringOutput());
            Assert.assertEquals("0(0,0) / 5(0,0)", calculator.calculate("0/5").getStringOutput());
            //Negative values
            Assert.assertEquals("-5(0,0) / 1(0,0)", calculator.calculate("(-5)").getStringOutput());
        } catch (WrongInputException | ParenthesesNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExceptionHandling() {
        try {
            calculator.calculate("5/0").getStringOutput();
            Assert.fail();
        } catch (ZeroDivisionException e) {
            Assert.assertTrue(true);
        } catch (WrongInputException | ParenthesesNotMatchingException e) {
            e.printStackTrace();
        }

        try {
            calculator.calculate("3.0/(3*0)").getStringOutput();
            Assert.fail();
        } catch (ZeroDivisionException e) {
            Assert.assertTrue(true);
        } catch (WrongInputException | ParenthesesNotMatchingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParenthesis() {
        try {
            Assert.assertEquals("8(0,0) / 1(0,0)", calculator.calculate("5+(1+2)").getStringOutput());
            Assert.assertEquals("8(0,0) / 1(0,0)", calculator.calculate("(3.0+5.0)").getStringOutput());
            Assert.assertEquals("9(0,0) / 1(0,0)", calculator.calculate("2*(1.2+3.3)").getStringOutput());
            Assert.assertEquals("29(0,0) / 2(0,0)", calculator.calculate("((2*5)+(1.2+3.3))").getStringOutput());
            Assert.assertEquals("6(0,0) / 1(0,0)", calculator.calculate("(1+2+3)").getStringOutput());
        } catch (WrongInputException | ParenthesesNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }

        try {
            calculator.calculate("()").getStringOutput();
        } catch(WrongInputException e) {
            Assert.assertTrue(true);
        } catch (ParenthesesNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }

        try {
            calculator.calculate("(1+1))").getStringOutput();
        } catch(ParenthesesNotMatchingException e) {
            Assert.assertTrue(true);
        } catch (WrongInputException | ZeroDivisionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCalculationWithPI() {
        try {
            Assert.assertEquals("1(1,0) / 1(0,0)", calculator.calculate("p").getStringOutput());
            Assert.assertEquals("5(1,0) / 1(0,0)", calculator.calculate("5*p").getStringOutput());
            Assert.assertEquals("5(1,0) / 1(0,0)", calculator.calculate("p*5").getStringOutput());
            Assert.assertEquals("1(1,0) / 2(0,0)", calculator.calculate("p/2").getStringOutput());
            Assert.assertEquals("5(0,0)+1(1,0) / 1(0,0)", calculator.calculate("5+p").getStringOutput());
            Assert.assertEquals("4(0,0)-1(1,0) / 1(0,0)", calculator.calculate("4-p").getStringOutput());
            Assert.assertEquals("2(1,0) / 1(0,0)", calculator.calculate("p+p").getStringOutput());
            Assert.assertEquals("0(1,0) / 1(0,0)", calculator.calculate("p-p").getStringOutput());
            Assert.assertEquals("3(0,0) / 1(0,0)", calculator.calculate("(3*p)/p").getStringOutput());
            Assert.assertEquals("2(1,0) / 1(0,0)", calculator.calculate("(4*p*p)/(2*p)").getStringOutput());

        } catch (WrongInputException | ParenthesesNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCalculationWithE() {
        try {
            Assert.assertEquals("1(0,1) / 1(0,0)", calculator.calculate("e").getStringOutput());
            Assert.assertEquals("5(0,1) / 1(0,0)", calculator.calculate("5*e").getStringOutput());
            Assert.assertEquals("5(0,1) / 1(0,0)", calculator.calculate("e*5").getStringOutput());
            Assert.assertEquals("1(0,1) / 2(0,0)", calculator.calculate("e/2").getStringOutput());
            Assert.assertEquals("5(0,0)+1(0,1) / 1(0,0)", calculator.calculate("5+e").getStringOutput());
            Assert.assertEquals("4(0,0)-1(0,1) / 1(0,0)", calculator.calculate("4-e").getStringOutput());
            Assert.assertEquals("2(0,1) / 1(0,0)", calculator.calculate("e+e").getStringOutput());
            Assert.assertEquals("0(0,1) / 1(0,0)", calculator.calculate("e-e").getStringOutput());
            Assert.assertEquals("10(0,0)+2(0,1) / 1(0,0)", calculator.calculate("(5+e)*2").getStringOutput());
            Assert.assertEquals("3(0,0) / 1(0,0)", calculator.calculate("(3*e)/e").getStringOutput());
            Assert.assertEquals("2(0,1) / 1(0,0)", calculator.calculate("(4*e*e)/(2*e)").getStringOutput());

        } catch (WrongInputException | ParenthesesNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCombinations() {
        try {
            Assert.assertEquals("3(0,0)+3(0,1)+3(1,0) / 1(0,0)", calculator.calculate("(1+p+e)*3").getStringOutput());
            Assert.assertEquals("6(0,0)+1(0,1)+1(1,0) / 1(0,0)", calculator.calculate("(1+p+e)+2+3").getStringOutput());
        } catch (WrongInputException | ParenthesesNotMatchingException | ZeroDivisionException e) {
            e.printStackTrace();
        }
    }
}