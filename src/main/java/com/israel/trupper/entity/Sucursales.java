package com.israel.trupper.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sucursales")
public class Sucursales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre",length = 50, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "sucursales", cascade = CascadeType.PERSIST)
    private List<Ordenes> ordenes;



    
    public Sucursales() {
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public List<Ordenes> getOrdenes() {
        return ordenes;
    }



    public void setOrdenes(List<Ordenes> ordenes) {
        this.ordenes = ordenes;
    }

    

    
}
