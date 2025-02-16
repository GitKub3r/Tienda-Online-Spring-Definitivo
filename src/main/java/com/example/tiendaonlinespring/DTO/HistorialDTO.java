package com.example.tiendaonlinespring.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class HistorialDTO {
    private Integer id;
    private Integer clientID;
    private Integer productID;
    private LocalDate purchaseDate;
    private Integer amount;
    private String type;
    private String description;
}
