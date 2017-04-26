package springbook.learningtest.jdk.jaxb;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import springbook.user.sqlservice.SqlType;
import springbook.user.sqlservice.Sqlmap;

public class JaxbTest {
	@Test
	public void readSqlmap() throws JAXBException, IOException {
		String contextPath = Sqlmap.class.getPackage().getName(); // contextPath = "springbook.user.sqlservice". Sqlmap.java의 package명
		JAXBContext context = JAXBContext.newInstance(contextPath); //바인딩용 클래스들 위치(springbook.user.sqlservice)를 가지고 JAXBContext를 만든다.
		
		Unmarshaller unmarshaller = context.createUnmarshaller(); // Xml에서 Java Object 로 변환을 unmarshalling. Java Object에서 Xml로 변환을 marshalling이라고 한다.
		
		Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(getClass().getResourceAsStream("/springbook/learningtest/xml/SQLUnmarshallingTest.xml"));
		
		List<SqlType> sqlList = sqlmap.getSql();
		
		assertEquals(sqlList.size(), 3);
		assertEquals(sqlList.get(0).getKey(), "add");
		assertEquals(sqlList.get(0).getValue(), "insert");
		assertEquals(sqlList.get(1).getKey(), "get");
		assertEquals(sqlList.get(1).getValue(), "select");
		assertEquals(sqlList.get(2).getKey(), "delete");
		assertEquals(sqlList.get(2).getValue(), "delete");
	}
}