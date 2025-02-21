package com.example.tiendaonlinespring.Service;

import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;


    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Cliente add(Cliente c) {
        return repo.save(c);
    }

    public Cliente update(Cliente c) {
        return repo.save(c);
    }

    public void delete(int id) {
        Cliente client = repo.findById(id).orElse(null);
        repo.delete(client);
    }
}
