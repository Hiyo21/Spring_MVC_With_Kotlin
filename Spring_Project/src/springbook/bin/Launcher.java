package springbook.bin;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;

public class Launcher{
	public static void main(String[] args) {
		List<String> testClasses = new ArrayList<String>();
		String daoTestClass = "springbook.user.dao.UserDaoTest";
		String jUnitTestClass = "springbook.learningtest.junit.JUnitTest";

		testClasses.add(daoTestClass);
		testClasses.add(jUnitTestClass);
		
		JUnitCore.main(testClasses.toArray(new String[0]));
	}
}