package com.store.technology.controllers;

import com.store.technology.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder= restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void getAll() {
        ResponseEntity<Laptop[]> response= testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        List<Laptop> laptops= Arrays.asList(response.getBody());
        assertEquals(0, laptops.size());
    }

    @Test
    void getById() {
        ResponseEntity<Laptop> response= testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404,response.getStatusCodeValue());
    }

    @Test
    void create() {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json= """
                {
                    "mark": "APPLE",
                    "model": 2022,
                    "price": 2000.67,
                    "color": "Blanco"
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);
        Laptop result= response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, result.getId());
    }

    @Test
    void update() {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json= """
                {
                    "id":1,
                    "mark": "APPLE",
                    "model": 2022,
                    "price": 2000.67,
                    "color": "Blanco"
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response =testRestTemplate.exchange("/api/laptops",HttpMethod.PUT,request,Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void delete() {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json= "";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response= testRestTemplate.exchange("/api/laptops/1", HttpMethod.DELETE,request,Laptop.class);
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
        assertEquals(204,response.getStatusCodeValue());
    }

    @Test
    void deleteAll() {
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json= "";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response= testRestTemplate.exchange("/api/laptops", HttpMethod.DELETE,request,Laptop.class);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(400,response.getStatusCodeValue());
    }
}