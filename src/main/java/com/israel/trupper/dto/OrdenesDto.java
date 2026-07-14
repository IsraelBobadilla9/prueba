package com.israel.trupper.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrdenesDto {
    
    private List<ProductosDto> productos;
    private SucursalDto sucursal;
    private BigDecimal total;
    private LocalDateTime fecha;

    public List<ProductosDto> getProductos() {
        return productos;
    }
    public void setProductos(List<ProductosDto> productos) {
        this.productos = productos;
    }
    public SucursalDto getSucursal() {
        return sucursal;
    }
    public void setSucursal(SucursalDto sucursal) {
        this.sucursal = sucursal;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


    
}
