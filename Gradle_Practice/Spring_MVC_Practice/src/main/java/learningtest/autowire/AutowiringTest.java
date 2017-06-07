package learningtest.autowire;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/autowireContext.xml")
public class AutowiringTest {

    @Autowired HelloBean helloBean;
    @Autowired AnnotatedHelloBean annotatedHelloBean;
    @Autowired ApplicationContext applicationContext;

    public void setHelloBean(HelloBean helloBean) {
        this.helloBean = helloBean;
    }

    @Test
    public void isApplicationContextAutowired() {
        Assert.assertNotNull(applicationContext);
    }

    @Test
    public void isBeanAutowiredXML()
    {
        Assert.assertEquals(this.helloBean.name, "Hello");
        Assert.assertEquals(this.helloBean.address, "Bean");
        Assert.assertEquals(this.helloBean.age, 14);
    }

    @Test
    public void isBeanAutowiredAnnotation() {
        Assert.assertNotNull(this.annotatedHelloBean);
    }

    @Test
    public void isBeanAutowiredConfigurationBean() {
        AnnotatedHelloBean annotatedHelloBeanByConfiguration = applicationContext.getBean("annotatedHelloBean", AnnotatedHelloBean.class);
        Assert.assertEquals(annotatedHelloBeanByConfiguration.getName(),"HelloAnno");
        Assert.assertEquals(annotatedHelloBeanByConfiguration.getAddress(),"BeanAnno");
        Assert.assertEquals(annotatedHelloBeanByConfiguration.getAge(),15);
    }
}
