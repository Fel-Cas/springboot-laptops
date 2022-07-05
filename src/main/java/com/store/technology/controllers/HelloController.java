package com.store.technology.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/saludo")
    public String hello(){
        return"Hola, ¿Cómo estás?";
    }
}
