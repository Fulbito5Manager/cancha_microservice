package com.api.cancha.controller;

import com.api.cancha.domain.Cancha;
import com.api.cancha.service.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/canchas")
public class CanchaController {
    @Autowired
    private CanchaService canchaService;
    @GetMapping
    public List<Cancha> getAllCanchas(){
        return canchaService.getAllCanchas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cancha> getCanchaById(@PathVariable Long id){
        Optional<Cancha> cancha = canchaService.getCanchaById(id);
        return cancha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Cancha createCancha(@RequestBody Cancha cancha){
        return canchaService.saveCancha(cancha);
    }
}
