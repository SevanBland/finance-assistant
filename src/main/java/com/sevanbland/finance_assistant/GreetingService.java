package com.sevanbland.finance_assistant;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public Greeting greet() {
        return new Greeting("Hello!");
    }
    public Greeting greet(String name) {
        return new Greeting("Hello, " + name + "!");
    }
}