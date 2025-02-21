package com.example.tiendaonlinespring.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class HistorialDTO {
    private Integer id;

    @NotNull(message = "El ID del cliente no puede ser nulo")
    private Integer clientID;

    @NotNull(message = "El ID del producto no puede ser nulo")
    private Integer productID;

    @NotNull(message = "La fecha de compra no puede ser nula")
    private LocalDate purchaseDate;

    @NotNull(message = "La cantidad no puede ser nula")
    private Integer amount;

    @NotBlank(message = "El tipo de transacción no puede estar vacío")
    private String type;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;
}
