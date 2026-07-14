package com.israel.trupper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.israel.trupper.dto.OrdenesDto;
import com.israel.trupper.service.OrdenesService;

@RestController
@RequestMapping("/ordenes")
public class OrdenesController {

    private final OrdenesService ordenesService;

    public OrdenesController(OrdenesService ordenesService) {
        this.ordenesService = ordenesService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> postMethodName(@RequestBody OrdenesDto request) {
        OrdenesDto creada = ordenesService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping("/ordeninfo/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Long id) {

        OrdenesDto  ordenesDto = ordenesService.findById(id);
        return ResponseEntity.ok().body(ordenesDto);
    }
}
