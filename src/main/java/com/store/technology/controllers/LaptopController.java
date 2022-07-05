package com.store.technology.controllers;

import com.store.technology.entities.Laptop;
import com.store.technology.respositories.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    // Obtener todas las Laptops
    @GetMapping("/api/laptops")
    public List<Laptop> getAll(){
        return this.laptopRepository.findAll();
    }

    // Obtener una laptop en especifico
    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> getById(@PathVariable Long id){
        if(!this.laptopRepository.existsById(id)) return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(this.laptopRepository.findById(id).get());
    }

    // Crear y guardar una Laptop en base de datos
    @PostMapping("/api/laptops")
    public  ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        if(laptop.getId()!=null) return  ResponseEntity.badRequest().build();
        return ResponseEntity.ok(this.laptopRepository.save(laptop));
    }

    // Actualizar una laptop
    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if(!this.laptopRepository.existsById(laptop.getId())) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.laptopRepository.save(laptop));
    }

    // Eliminar una laptop en especifico
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if(!this.laptopRepository.existsById(id)) return  ResponseEntity.notFound().build();
        this.laptopRepository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
    // Eliminar todas las laptop
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        System.out.println(this.laptopRepository.count());
        if(this.laptopRepository.count()==0) return ResponseEntity.badRequest().build();
        this.laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
