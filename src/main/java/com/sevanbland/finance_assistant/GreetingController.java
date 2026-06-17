package com.sevanbland.finance_assistant;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class GreetingController {
    private final GreetingService greetingService;

    // Spring sees this constructor and automatically passes in the GreetingService bean
    public GreetingController(GreetingService greetingService) { // dependency injection
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting") // GET /greeting -> general greeting
    public Greeting greeting() {
        return greetingService.greet();
    }

    @GetMapping("/greeting/{name}") // GET /greeting/name -> greet user
    public Greeting greeting(@PathVariable String name) {
        return greetingService.greet(name);
    }
}