package com.israel.trupper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.israel.trupper.entity.Sucursales;

@Repository
public interface SucursalesRepository extends JpaRepository<Sucursales, Long> {

    Optional<Sucursales> findByNombre(String nombre);

}
