package springbook.learningtest.jdk;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloUppercaseTest {
		@Test
		public void helloUpperCaseTest(){
			Hello proxiHello = new HelloUppercase(new HelloTarget());
			
			assertEquals(proxiHello.sayHello("Toby"), "Hello TOBY");
			assertEquals(proxiHello.sayHi("Toby"), "Hi TOBY");
			assertEquals(proxiHello.sayThankYou("Toby"), "Thank You TOBY");
		}
}
