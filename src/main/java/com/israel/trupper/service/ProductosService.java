package com.israel.trupper.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.israel.trupper.dto.ProductosDto;
import com.israel.trupper.entity.Productos;
import com.israel.trupper.repository.ProductosRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductosService {
    private final ProductosRepository productosRepository;

    public ProductosService(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }
    
    @Transactional
    public ProductosDto crear(Long id ,ProductosDto productosDto){
        Optional<Productos> prodOptional = productosRepository.findById(id);
        if(prodOptional.isPresent()){
            Productos prop = prodOptional.get();
            prop.setPrecio(productosDto.getPrecio());
            productosRepository.save(prop);
            return productosDto;
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Producto no encontrado");

        

    }
}
