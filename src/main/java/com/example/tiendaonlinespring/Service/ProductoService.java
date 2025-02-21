package com.example.tiendaonlinespring.Service;

import com.example.tiendaonlinespring.Modelo.Producto;
import com.example.tiendaonlinespring.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository repo;

    public List<Producto> findAll() {
        return repo.findAll();
    }

    public Producto findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public String add(Producto p) {
        if(repo.existsByNombre(p.getNombre())) {
            return "existe";
        }

        repo.save(p);
        return "añadido";
    }

    public Producto update(Producto p) {
        return  repo.save(p);
    }

    public void delete(int id) {
        Producto product = repo.findById(id).orElse(null);
        repo.delete(product);
    }
}
