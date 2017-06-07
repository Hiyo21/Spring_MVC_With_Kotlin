package learningtest.autowire;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 과 @Bean 은 자바에서 생성하는 Bean factory method와 같은 것.
 * factory method에 더해서 meta정보가 자동 추가된다는 점에서 더 강력하다.
 * 자바 코드에서 property에 값을 넣어줌.
 * @Component와 같이 Annotation을 통해 autowire되는 빈을 생성하기 힘든 환경일 경우,
 * 또한 XML을 사용하지 않는 환경이라면 이 방법을 쓰면 좋다. 좀 verbose하지만서도.
 */
@Configuration
public class AnnotatedHelloBeanConfiguration {

    @Bean
    public AnnotatedHelloBean annotatedHelloBean(){
        AnnotatedHelloBean annotatedHelloBean = new AnnotatedHelloBean();
        annotatedHelloBean.setName("HelloAnno");
        annotatedHelloBean.setAddress("BeanAnno");
        annotatedHelloBean.setAge(15);
        return annotatedHelloBean;
    }
}
