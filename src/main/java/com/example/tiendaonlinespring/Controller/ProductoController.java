package com.example.tiendaonlinespring.Controller;

import com.example.tiendaonlinespring.DTO.ProductoDTO;
import com.example.tiendaonlinespring.Modelo.Producto;
import com.example.tiendaonlinespring.Service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Producto>> getAllProducts() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
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

        Producto p = product.createProducto(product);
        String caso = service.add(p);

        switch (caso) {
            case "existe":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre del producto ya existe");
            case "añadido":
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha añadido un nuevo producto");
            default:
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha añadido un nuevo producto");
        }
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
        Producto p = service.update(product.createProductoWithID(product, id));
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
