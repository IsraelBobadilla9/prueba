package com.israel.trupper.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "ordenes")
public class Ordenes {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")   
    private LocalDateTime fecha;
    @Column(name = "total")   
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursales sucursales;

    @OneToMany(mappedBy = "ordenes", cascade = CascadeType.PERSIST)
    private List<Productos> productos;

    public Ordenes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Sucursales getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursales sucursales) {
        this.sucursales = sucursales;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

}
