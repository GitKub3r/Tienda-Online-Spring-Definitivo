package com.example.tiendaonlinespring.Service;

import com.example.tiendaonlinespring.DTO.ClienteDTO;
import com.example.tiendaonlinespring.DTO.ProductoDTO;
import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Modelo.Producto;
import com.example.tiendaonlinespring.Repository.ClienteRepository;
import com.example.tiendaonlinespring.Repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    ProductoRepository repo;

    public ProductoService(ProductoRepository productoRepository) {
        this.repo = productoRepository;
    }

    public List<Producto> findAll() {
        return repo.findAll();
    }

    public Producto findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public ResponseEntity<String> add(ProductoDTO product) {
        Producto p = createProducto(product);
        repo.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body("Se ha añadido un nuevo producto");
    }

    public ResponseEntity<String> update(ProductoDTO product, int id) {
        Producto p = createProductoWithID(product, id);
        repo.save(p);
        return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado un nuevo producto");
    }

    public ResponseEntity<String> delete(int id) {
        Producto product = repo.findById(id).orElse(null);
        repo.delete(product);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Se ha eliminado un nuevo producto");
    }

    public Producto createProducto(ProductoDTO productoDTO) {
        Producto p = new Producto();
        p.setNombre(productoDTO.getName());
        p.setDescripcion(productoDTO.getDescription());
        p.setPrecio(productoDTO.getPrice());
        p.setStock(productoDTO.getStock());

        return p;
    }

    public Producto createProductoWithID(ProductoDTO productoDTO, int id) {
        Producto p = new Producto();
        p.setNombre(productoDTO.getName());
        p.setDescripcion(productoDTO.getDescription());
        p.setPrecio(productoDTO.getPrice());
        p.setStock(productoDTO.getStock());
        p.setId(id);

        return p;
    }
}
