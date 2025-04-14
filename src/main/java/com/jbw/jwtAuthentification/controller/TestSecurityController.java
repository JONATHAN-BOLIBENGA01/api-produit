package com.jbw.jwtAuthentification.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {
    @GetMapping("/private")
    public void sayHello(){
        System.out.println("hello, spring security and jwt authentification !");
    }
}
