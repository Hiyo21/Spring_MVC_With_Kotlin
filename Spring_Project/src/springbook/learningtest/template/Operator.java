package springbook.learningtest.template;

public enum Operator {
	ADDITION('+'),
	SUBTRACTION('-'),
	MULTIPLICATION('*'),
	DIVISION('/');
	
	private final char operator;
	
	Operator(char op) { operator = op; }
	
	public char getOperator() { return operator; } 
}
