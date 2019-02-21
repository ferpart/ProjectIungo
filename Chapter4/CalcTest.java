// Introduction to Software Testing
// Authors: Paul Ammann & Jeff Offutt
// Chapter 3; page ??
// JUnit for Calc.java
package junitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

class CalcTest {

	   @Test public void testAdd()
	   {
	      assertTrue ("Calc sum incorrect", 5 == Calc.add (2, 3));
	   }
	   
	   @Test public void testSubstract()
	   {
	      assertTrue ("Calc substraction incorrect", -1 == Calc.substract (2, 3));
	   }
	   
	   @Test public void testMultiply()
	   {
	      assertTrue ("Calc multiplication incorrect", 6 == Calc.multiply (2, 3));
	   }
	   
	   @Test public void testDivide()
	   {
		  try {
			  assertTrue ("Calc sum incorrect", 0.00001 == Calc.divide (1, 100000));  //Causes underflow
		  }
		  catch(ArithmeticException e) {
			  return;
		  }
		  fail("ArithmeticException expected");
	   }

}
