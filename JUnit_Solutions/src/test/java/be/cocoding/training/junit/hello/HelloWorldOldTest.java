package be.cocoding.training.junit.hello;

import java.util.Objects;

public class HelloWorldOldTest {

    public static void main(String[] args) {
        HelloWorld hw = new HelloWorld();
        String actual = hw.sayHello();
        if(!Objects.equals("Hello World !", actual)){
            throw new IllegalStateException("Actual result is not valid. Actual: " + actual);
        }
    }



}