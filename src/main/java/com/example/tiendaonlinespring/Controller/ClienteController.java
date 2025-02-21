package com.example.tiendaonlinespring.Controller;

import com.example.tiendaonlinespring.DTO.ClienteDTO;
import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> getAllClients() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Cliente getClientById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addClient(@Valid @RequestBody ClienteDTO client, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        return service.add(client);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateClient(@Valid @RequestBody ClienteDTO client, BindingResult result, @PathVariable int id) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        return service.update(client, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) {
        return service.delete(id);
    }
}
