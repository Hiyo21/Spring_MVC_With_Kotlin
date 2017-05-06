package springbook.learningtest.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/springbook/learningtest/junit/junit.xml")
public class JUnitTest {
	@Autowired
	ApplicationContext context;
	
	static Set<JUnitTest> testObjectSet = new HashSet<JUnitTest>();
	static ApplicationContext contextObject = null;
	
	@Test
	public void test1() {
		assertThat(testObjectSet, not(hasItem(this)));
		testObjectSet.add(this);
		
		assertTrue(contextObject == null || this.context == contextObject);
		contextObject = context;
	}
	
	@Test
	public void test2() {
		assertThat(testObjectSet, not(hasItem(this)));
		testObjectSet.add(this);
		
		assertTrue(contextObject == null || this.context == contextObject);
		contextObject = context;
	}
	
	@Test
	public void test3() {
		assertThat(testObjectSet, not(hasItem(this)));
		testObjectSet.add(this);
		
		assertTrue(contextObject == null || this.context == contextObject);
		contextObject = context;
	}
}
