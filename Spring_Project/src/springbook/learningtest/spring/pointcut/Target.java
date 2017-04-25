package springbook.learningtest.spring.pointcut;

public class Target implements TargetInterface {
	public void Hello() {}
	public void Hello(String s) {}
	public int minus(int a, int b) throws RuntimeException { return 0; }
	public int plus(int a, int b) throws RuntimeException { return 0; }
	public void method() {}
}
