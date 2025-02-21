package com.example.tiendaonlinespring.Controller;

import com.example.tiendaonlinespring.DTO.HistorialDTO;
import com.example.tiendaonlinespring.DTO.ProductoDTO;
import com.example.tiendaonlinespring.Modelo.Historial;
import com.example.tiendaonlinespring.Modelo.Producto;
import com.example.tiendaonlinespring.Service.HistorialService;
import com.example.tiendaonlinespring.Service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    @Autowired
    private HistorialService service;

    @GetMapping
    public List<Historial> getAllHistorials() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Historial getHistorialById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addHistorial(@Valid @RequestBody HistorialDTO historial, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        return service.add(historial);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateHistorial(@Valid @RequestBody HistorialDTO historial, BindingResult result, @PathVariable int id) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        return service.update(historial, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHistorial(@PathVariable int id) {
        return service.delete(id);
    }
}
