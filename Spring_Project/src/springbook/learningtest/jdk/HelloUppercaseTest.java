package springbook.learningtest.jdk;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloUppercaseTest {
		@Test
		public void helloUpperCaseTest(){
			Hello proxiHello = new HelloUppercase(new HelloTarget());
			
			assertEquals(proxiHello.sayHello("Toby"), "Hello TOBY");
			assertEquals(proxiHello.sayHi("Toby"), "Hi TOBY");
			assertEquals(proxiHello.sayThankYou("Toby"), "Thank You TOBY");
		}
}
