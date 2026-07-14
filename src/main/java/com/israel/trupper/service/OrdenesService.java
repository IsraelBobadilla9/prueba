package com.israel.trupper.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.israel.trupper.dto.OrdenesDto;
import com.israel.trupper.dto.ProductosDto;
import com.israel.trupper.dto.SucursalDto;
import com.israel.trupper.entity.Ordenes;
import com.israel.trupper.entity.Productos;
import com.israel.trupper.entity.Sucursales;
import com.israel.trupper.repository.OrdenesRepository;
import com.israel.trupper.repository.SucursalesRepository;

import jakarta.transaction.Transactional;

@Service
public class OrdenesService {

    private final OrdenesRepository ordenesRepository;
    private final SucursalesRepository sucursalesRepository;

    public OrdenesService(OrdenesRepository ordenesRepository, SucursalesRepository sucursalesRepository) {
        this.ordenesRepository = ordenesRepository;
        this.sucursalesRepository = sucursalesRepository;
    }
    @Transactional
    public OrdenesDto crear(OrdenesDto ordenesDto) {
        Sucursales sucursal = sucursalesRepository.findByNombre(ordenesDto.getSucursal().getNombre())
                .orElseGet(() -> {
                    Sucursales nueva = new Sucursales();
                    nueva.setNombre(ordenesDto.getSucursal().getNombre());
                    return sucursalesRepository.save(nueva);
                });

        Ordenes orden = new Ordenes();
        orden.setFecha(ordenesDto.getFecha() != null ? ordenesDto.getFecha() : LocalDateTime.now());
        orden.setTotal(ordenesDto.getTotal());
        orden.setSucursales(sucursal);

        List<Productos> productos = ordenesDto.getProductos().stream()
                .map(productoDto -> mapProducto(productoDto, orden))
                .collect(Collectors.toList());
        orden.setProductos(productos);

        ordenesRepository.save(orden);

        return ordenesDto;
    }

    private Productos mapProducto(ProductosDto productoDto, Ordenes orden) {
        Productos producto = new Productos();
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setPrecio(productoDto.getPrecio());
        producto.setOrdenes(orden);
        return producto;
    }

    @Transactional
    public OrdenesDto findById(Long id){
        Optional<Ordenes> ordOptional = ordenesRepository.findById(id);
        if(ordOptional.isPresent()){
            OrdenesDto orden =    ordensToDto(ordOptional.get());
            return orden;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Producto no encontrado");


    }

    private OrdenesDto ordensToDto(Ordenes orden){
        OrdenesDto ordenesDto = new OrdenesDto();
        ordenesDto.setFecha(orden.getFecha());
        

        SucursalDto sucursal = new SucursalDto();
        sucursal.setNombre(orden.getSucursales().getNombre());

        List<ProductosDto> productosDtos = orden.getProductos().stream().map( producto -> {
            ProductosDto productosDto = new ProductosDto();
            productosDto.setDescripcion(producto.getDescripcion());
            productosDto.setPrecio(producto.getPrecio());
            return productosDto;
        }).collect(Collectors.toList());

        ordenesDto.setProductos(productosDtos);
        ordenesDto.setSucursal(sucursal);
        return ordenesDto;
    }




}
