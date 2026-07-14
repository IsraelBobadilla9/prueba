package com.israel.trupper.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.israel.trupper.dto.ProductosDto;
import com.israel.trupper.service.ProductosService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/productos")
public class ProductosController {
    
    private final ProductosService productosService; 

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id, @RequestBody ProductosDto request) {
        
        ProductosDto updateDto = productosService.crear(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateDto);    
    }

    
    
}
