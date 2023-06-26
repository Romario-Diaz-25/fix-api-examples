package com.fixapi.fixapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salutte")
public class HelloWorldController {
    @GetMapping("/hii")
    public String salutte() {
        return "FIX API CONNECTION STARTING";
    }
}
