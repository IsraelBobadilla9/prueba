package com.israel.trupper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.israel.trupper.entity.Ordenes;

@Repository
public interface OrdenesRepository extends JpaRepository<Ordenes, Long> {

}
