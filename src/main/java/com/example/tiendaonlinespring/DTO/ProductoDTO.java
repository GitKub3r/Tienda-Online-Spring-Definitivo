package com.example.tiendaonlinespring.DTO;

import com.example.tiendaonlinespring.Modelo.Producto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {
    private Integer id;

    @NotBlank(message = "El nombre del producto no puede estar vacío ni contener solo espacios")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ ]+$", message = "El nombre del producto solo puede contener caracteres alfanuméricos")
    private String name;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.00", message = "El precio debe ser un valor positivo o cero")
    private BigDecimal price;

    @NotNull(message = "El stock no puede ser nulo")
    private Integer stock;

    public Producto createProducto(ProductoDTO productoDTO) {
        Producto p = new Producto();
        p.setNombre(productoDTO.getName());
        p.setDescripcion(productoDTO.getDescription());
        p.setPrecio(productoDTO.getPrice());
        p.setStock(productoDTO.getStock());

        return p;
    }

    public Producto createProductoWithID(ProductoDTO productoDTO, int id) {
        Producto p = new Producto();
        p.setNombre(productoDTO.getName());
        p.setDescripcion(productoDTO.getDescription());
        p.setPrecio(productoDTO.getPrice());
        p.setStock(productoDTO.getStock());
        p.setId(id);

        return p;
    }
}
