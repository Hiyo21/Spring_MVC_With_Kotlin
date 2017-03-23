package springbook.learningtest.template;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Calculator calculator;
	String filePath;
		
	@Before
	public void setCalculator(){
		this.calculator = new Calculator();
		filePath = getClass().getResource("numbers.txt").getPath();
	}
	
	@Test
	public void sumOfNumbers() throws IOException {
		int sum = calculator.calculate(filePath, Operator.ADDITION.getOperator());
		assertEquals(sum, 10);
	}
	
	@Test
	public void subtractionOfNumbers() throws IOException {
		int difference = calculator.calculate(filePath, Operator.SUBTRACTION.getOperator());
		assertEquals(difference, -10);
	}
	
	@Test
	public void concatStrings() throws IOException {
		String result = calculator.concatenate(filePath);
		assertEquals(result, "1234");
	}
}

