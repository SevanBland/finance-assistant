package com.sevanbland.finance_assistant;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    @GetMapping("/") // GET / -> hello
    public String hello() {
        return "Hello from finance-assistant!";
    }
}