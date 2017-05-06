package springbook.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import springbook.user.service.UserService;
import springbook.user.service.UserServiceImpl;

/**
 * Created by Chris on 2017-05-06.
 */
@Configuration
public class TestAppContext {

    @Bean
    public UserService testUserService() {
        UserService userService = new UserServiceImpl();
        return userService;
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setUsername("shoonara21@gmail.com");
        javaMailSender.setPassword("clee2249l");
        return javaMailSender;
    }
}
