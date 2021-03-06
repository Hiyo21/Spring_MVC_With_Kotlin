package springbook.learningtest.oxm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.sqlservice.SqlType;
import springbook.user.sqlservice.Sqlmap;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/OxmTest-context.xml")
public class OxmTest {
	@Autowired Unmarshaller unmarshaller;
	
	@Test
	public void unmarshallSqlMap() throws XmlMappingException, IOException {
		Source xmlSource = new StreamSource(getClass().getResourceAsStream("/sqlmap.xml"));
		Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(xmlSource);

		List<SqlType> sqlTypeList = sqlmap.getSql();
		
		assertEquals(sqlTypeList.size(), 6);
		
		String[] keyList = new String[] {"userAdd", "userGet", "userDeleteAll", "userGetCount", "userUpdate", "userGetAll"};
		
		for(int i = 0 ; i < 5 ; i++) {
			assertEquals(sqlTypeList.get(i).getKey(), keyList[i]);
		}
	}
}