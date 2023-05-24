package com.example.camelexperiment.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("greeter")
public class Greeter {
    private int counter;
    @Value("${greeting}")
    private String say;

    public String greet(String body) {
        return String.format("%s I am invoked %d times", say, ++counter);
    }
}
