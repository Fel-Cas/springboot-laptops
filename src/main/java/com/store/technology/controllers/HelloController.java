package com.store.technology.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${app.mensaje}")
    String message;
    @GetMapping("/saludo")
    public String hello(){
        System.out.println(message);
        return"Hola, ¿Cómo estás?" ;
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
