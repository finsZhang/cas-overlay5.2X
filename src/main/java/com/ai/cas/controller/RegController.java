package com.ai.cas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegController {

    @GetMapping("/test")
    public String test(){
        return "hello world";
    }
}
