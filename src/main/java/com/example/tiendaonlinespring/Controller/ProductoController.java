package com.example.tiendaonlinespring.Controller;

import com.example.tiendaonlinespring.DTO.ProductoDTO;
import com.example.tiendaonlinespring.Modelo.Producto;
import com.example.tiendaonlinespring.Service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> getAllProducts() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Producto getProductById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductoDTO product, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        return service.add(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductoDTO product, BindingResult result, @PathVariable int id) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        return service.update(product, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return service.delete(id);
    }
}
