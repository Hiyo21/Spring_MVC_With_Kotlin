package learningtest.autowire;

import org.springframework.stereotype.Component;

/**
 * Created by shoon on 2017/06/03.
 */
@Component
public class AnnotatedHelloBean {
    String name;
    String address;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
