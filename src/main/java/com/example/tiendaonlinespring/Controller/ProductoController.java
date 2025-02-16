package com.example.tiendaonlinespring.Controller;

import com.example.tiendaonlinespring.DTO.ProductoDTO;
import com.example.tiendaonlinespring.DTO.ProductoDTO;
import com.example.tiendaonlinespring.Modelo.Producto;
import com.example.tiendaonlinespring.Modelo.Producto;
import com.example.tiendaonlinespring.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addProduct(@RequestBody ProductoDTO product) {
        return service.add(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductoDTO product, @PathVariable int id) {
        return service.update(product, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return service.delete(id);
    }
}
