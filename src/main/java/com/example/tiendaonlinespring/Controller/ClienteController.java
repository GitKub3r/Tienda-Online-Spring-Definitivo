package com.example.tiendaonlinespring.Controller;

import com.example.tiendaonlinespring.DTO.ClienteDTO;
import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Cliente>> getAllClients() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> getClientById(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
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
        service.add(client.createCliente(client));
        return ResponseEntity.status(HttpStatus.CREATED).body("Se ha añadido un nuevo cliente");
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
        Cliente c = client.createClientWithID(client, id);
        service.update(c);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
