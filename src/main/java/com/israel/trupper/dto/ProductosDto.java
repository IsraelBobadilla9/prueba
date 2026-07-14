package com.israel.trupper.dto;

import java.math.BigDecimal;

public class ProductosDto {

    private BigDecimal precio;
    private String descripcion;


    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public ProductosDto() {
    }
    public ProductosDto(BigDecimal precio, String descripcion) {
        this.precio = precio;
        this.descripcion = descripcion;
    }

    


    
}
