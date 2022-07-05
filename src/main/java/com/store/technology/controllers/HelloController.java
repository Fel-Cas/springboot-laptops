package com.store.technology.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/saludo")
    public String hello(){
        return"Hola, ¿Cómo estás?";
    }

    @GetMapping("/")
    public String info(){
        return """
                <h1>Información de la API</h1>
                <p>
                Esta es una API REST hecha con spring, en la que se puede hacer un CRUD de laptops.
                Es mi primer CRUD con Spring Boot, lo estoy aprendiendo.
                </p>
                """;
    }
}
