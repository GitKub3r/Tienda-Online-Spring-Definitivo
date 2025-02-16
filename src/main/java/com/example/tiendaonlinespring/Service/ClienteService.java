package com.example.tiendaonlinespring.Service;

import com.example.tiendaonlinespring.DTO.ClienteDTO;
import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    ClienteRepository repo;

    public ClienteService(ClienteRepository clienteRepository) {
        this.repo = clienteRepository;
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public ResponseEntity<String> add(ClienteDTO cliente) {
        Cliente c = createCliente(cliente);
        repo.save(c);
        return ResponseEntity.status(HttpStatus.CREATED).body("Se ha añadido un nuevo cliente");
    }

    public ResponseEntity<String> update(ClienteDTO cliente, int id) {
        Cliente c = createClientWithID(cliente, id);
        repo.save(c);
        return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado un nuevo cliente");
    }

    public ResponseEntity<String> delete(int id) {
        Cliente client = repo.findById(id).orElse(null);
        repo.delete(client);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Se ha eliminado un nuevo cliente");
    }

    public Cliente createCliente(ClienteDTO clienteDTO) {
        Cliente c = new Cliente();
        c.setNombre(clienteDTO.getName());
        c.setApellido(clienteDTO.getSurname());
        c.setNickname(clienteDTO.getNickname());
        c.setPassword(clienteDTO.getPassword());
        c.setTelefono(clienteDTO.getPhone());
        c.setDomicilio(clienteDTO.getAddress());

        return c;
    }

    public Cliente createClientWithID(ClienteDTO clienteDTO, int id) {
        Cliente c = new Cliente();
        c.setNombre(clienteDTO.getName());
        c.setApellido(clienteDTO.getSurname());
        c.setNickname(clienteDTO.getNickname());
        c.setPassword(clienteDTO.getPassword());
        c.setTelefono(clienteDTO.getPhone());
        c.setDomicilio(clienteDTO.getAddress());
        c.setId(id);

        return c;
    }
}
