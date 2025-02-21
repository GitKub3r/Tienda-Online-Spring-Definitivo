package com.example.tiendaonlinespring.Service;

import com.example.tiendaonlinespring.Modelo.Historial;
import com.example.tiendaonlinespring.Modelo.Producto;
import com.example.tiendaonlinespring.Repository.HistorialRepository;
import com.example.tiendaonlinespring.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HistorialService {

    @Autowired
    HistorialRepository repo;

    @Autowired
    ProductoRepository productRepository;

    public List<Historial> findAll() {
        return repo.findAll();
    }

    public Historial findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public String add(Historial h) {
        Producto product = h.getProducto();

        switch (h.getTipo()) {
            case "Compra":
                if (product.getStock() < h.getCantidad()) {
                    return "stock";
                } else {
                    repo.save(h);
                    product.setStock(product.getStock() - h.getCantidad());
                    productRepository.save(product);
                    return "ok";
                }

            case "Devolución":
                long remainDays = ChronoUnit.DAYS.between(h.getFechaCompra(), LocalDate.now());

                if (remainDays > 30) {
                    return "dias";
                } else {
                    repo.save(h);
                    product.setStock(product.getStock() + h.getCantidad());
                    productRepository.save(product);
                    return "ok";
                }
            default:
                return "incorrecto";
        }
    }

    public Historial update(Historial h) {
        return repo.save(h);
    }

    public void delete(int id) {
        Historial historial = repo.findById(id).orElse(null);
        repo.delete(historial);
    }
}
