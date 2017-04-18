package springbook.learningtest.jdk;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectionTest {
	@Test
	public void invokeMethod() throws Exception {
		String name = "Spring";
		
		assertEquals(name.length(), 6);
		
		Method lengthMethod = name.getClass().getMethod("length");
		assertEquals((int)lengthMethod.invoke(name), 6);
		
		Method charAtMethod = name.getClass().getMethod("charAt", int.class);
		assertEquals(name.charAt(0), (char)charAtMethod.invoke(name, 0));
	}
	
	@Test
	public void simpleProxy() {
		Hello hello = new HelloTarget();
		assertEquals(hello.sayHello("Toby"),("Hello Toby"));
		assertEquals(hello.sayHi("Toby"),("Hi Toby"));
		assertEquals(hello.sayThankYou("Toby"),("Thank You Toby"));
	}
}