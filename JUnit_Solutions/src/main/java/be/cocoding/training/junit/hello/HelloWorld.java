package be.cocoding.training.junit.hello;

import java.util.Objects;

public class HelloWorld {

    public String sayHello(){
        return "Hello World !";
    }

    public String sayHello(String name){
        Objects.requireNonNull(name, "Given 'name' parameter cannot be null");
        return "Hello " + name + " !";
    }

}
