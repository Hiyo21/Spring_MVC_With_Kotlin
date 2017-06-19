package learningtest.web.fundamental.model;

/**
 * Created by shoon on 2017/06/19.
 */
public class HelloWorld {
    int id;
    String name;
    String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String sayHello(String name) {
        return "Hello! " + name;
    }
}
