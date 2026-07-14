package com.israel.trupper.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", length = 20)
    private String codigo;
    @Column(name = "descripcion", length = 200)
    private String descripcion;
    @Column(name = "precio")   
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Ordenes ordenes;

    public Productos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Ordenes getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(Ordenes ordenes) {
        this.ordenes = ordenes;
    }

    
    


}
