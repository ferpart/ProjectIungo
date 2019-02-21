// Introduction to Software Testing
// Authors: Paul Ammann & Jeff Offutt
// Chapter 3; page ??
// See CalcTest.java, DataDrivenCalcTest.java for JUnit tests

package junitTests;

public class Calc1 {   // Calc program before passing all the tests, remove '1' before running CalcTest.java

	static public int add (int a, int b)
	{
		return a + b;
	}	
	
	static public int substract(int a, int b)
	{
		return a - b;
	}
	
	static public int multiply(int a, int b) {
		return a * b;
	}
	
	static public int divide(int a, int b) {
		if(b == 0) throw new ArithmeticException("Calc.divide");
		return a/b;
	}
}
