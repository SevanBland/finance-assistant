package com.sevanbland.finance_assistant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private final GreetingService greetingService;

    // Spring sees this constructor and automatically passes in the GreetingService bean
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public Greeting greeting() {
        return greetingService.greet();
    }

    @GetMapping("/greeting/{name}")
    public Greeting greeting(@PathVariable String name) {
        return greetingService.greet(name);
    }
}