package com.example.tiendaonlinespring.DTO;

import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Modelo.Historial;
import com.example.tiendaonlinespring.Modelo.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^(Compra|Devolución)$", message = "Solo se admite la palabra Compra o Devolución")
    private String type;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    public Historial createHistorial(HistorialDTO historialDTO) {
        Historial h = new Historial();

        h.setFechaCompra(historialDTO.getPurchaseDate());
        h.setCantidad(historialDTO.getAmount());
        h.setTipo(historialDTO.getType());
        h.setDescripcion(historialDTO.getDescription());

        return h;
    }

    public Historial createHistorialWithID(HistorialDTO historialDTO, int id) {
        Historial h = new Historial();

        h.setFechaCompra(historialDTO.getPurchaseDate());
        h.setCantidad(historialDTO.getAmount());
        h.setTipo(historialDTO.getType());
        h.setDescripcion(historialDTO.getDescription());
        h.setId(id);

        return h;
    }
}
