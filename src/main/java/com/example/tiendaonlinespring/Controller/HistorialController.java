package com.example.tiendaonlinespring.Controller;

import com.example.tiendaonlinespring.DTO.HistorialDTO;
import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Modelo.Historial;
import com.example.tiendaonlinespring.Modelo.Producto;
import com.example.tiendaonlinespring.Service.HistorialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    @Autowired
    private HistorialService service;

    @GetMapping
    public ResponseEntity<List<Historial>> getAllHistorials() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Historial> getHistorialById(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addHistorial(@Valid @RequestBody HistorialDTO historial, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        Historial historial1 = historial.createHistorial(historial);
        Cliente cliente = service.findById(historial.getClientID()).getCliente();
        historial1.setCliente(cliente);
        Producto producto = service.findById(historial.getProductID()).getProducto();
        historial1.setProducto(producto);
        String texto = service.add(historial1);
        switch (texto) {
            case "ok":
                return ResponseEntity.status(HttpStatus.OK).body("Compra/Devolucion procesada corractamente");
            case "stock":
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Stock insuficiente: Solo quedan " + producto.getStock() + " unidades disponibles");
            case "dias":
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Han pasado más de 30 días desde la compra");
            default:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Tipo de historial incorrecto");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateHistorial(@Valid @RequestBody HistorialDTO historial, BindingResult result, @PathVariable int id) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        Historial h = historial.createHistorialWithID(historial, id);
        return ResponseEntity.status(HttpStatus.OK).body(h);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteHistorial(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
