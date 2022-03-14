# Fraction Calculator

Core system codes are at https://github.com/Jin4202/FractionCalculator/tree/master/app/src/main/java/com/calculator/fractioncalculator    

## Project Environment Build
- Framework: Android Studio   
- Language: Java  

## Project goal
**Creating a calculator that can represent the answer in both decimal and fraction format.**   

#### Main Challenges
1. Transforming the answer from decimal to fraction   
Solution: Independently build the arithmetic operations and input parsers.   
Ex. inputs: n1, n2
<pre>
Addtion: (n1.numerator*n2.denominator + n2.numerator*n1.denominator) / (n1.denominator*n2.denominator)
Minus: (n1.numerator*n2.denominator - n2.numerator*n1.denominator) / (n1.denominator*n2.denominator)
Multiplication: (n1.numerator*n2.numerator) / (n1.denominator*n2.denominator)
Division: (n1.numerator*n2.denominator) / (n1.denominator*n2.numerator)
</pre>
2. Calculating while leaving the mathematical constant in the expressions   
Basic idea: Creating a map that contains infomation of powers of constants and their coefficients.   
**PI-e Map**   
a(i,j) = a * PI<sup>i</sup> * e<sup>j</sup>   
   
Examples)   
**Addition/Minus**   
<img src="/RmImgs/Addition.png" width="730px" height="250px" title="Addition_Minus"></img><br/>
a(i,j) + b(i,j) = (a+b)(i,j)   
**Multiplication**   
<img src="/RmImgs/Multiply.png" width="730px" height="250px" title="Multiply"></img><br/>
a(i<sub>1</sub>,j<sub>1</sub>) x b(i<sub>2</sub>,j<sub>2</sub>) = (axb)(i<sub>1</sub>+i<sub>2</sub>,j<sub>1</sub>+j<sub>2</sub>)   
**Division**   
<img src="/RmImgs/Division.png" width="730px" height="250px" title="Division"></img><br/>
2a(i<sub>1</sub>,j) / 2(i<sub>2</sub>,j) = a(i<sub>1</sub>-i<sub>2</sub>,j)   
*Challenge    
Only way of finding common polynominal factor seems to be trying every single formula. Therefore, I only implemented the factorization of 'single' constant {a(0,0)} and mathematical constants.*

## Further possible goals
- Trigonometry
- Logarithm
- Root
- Degree/Radian
- Factoring polymoninal
