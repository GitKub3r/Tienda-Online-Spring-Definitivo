package com.example.tiendaonlinespring.Controller;

import com.example.tiendaonlinespring.DTO.ClienteDTO;
import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addClient(@RequestBody ClienteDTO client) {
        return service.add(client);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateClient(@RequestBody ClienteDTO client, @PathVariable int id) {
        return service.update(client, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) {
        return service.delete(id);
    }
}
