package com.example.tiendaonlinespring.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
}
