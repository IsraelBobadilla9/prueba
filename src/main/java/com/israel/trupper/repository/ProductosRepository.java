package com.israel.trupper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.israel.trupper.entity.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long>{

    
    
}
