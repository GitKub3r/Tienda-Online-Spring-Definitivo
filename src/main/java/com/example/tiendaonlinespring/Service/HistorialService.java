package com.example.tiendaonlinespring.Service;

import com.example.tiendaonlinespring.DTO.HistorialDTO;
import com.example.tiendaonlinespring.DTO.ProductoDTO;
import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Modelo.Historial;
import com.example.tiendaonlinespring.Modelo.Producto;
import com.example.tiendaonlinespring.Repository.HistorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class HistorialService {

    HistorialRepository repo;
    ClienteService clientService;
    ProductoService productService;

    public HistorialService(HistorialRepository historialRepository, ClienteService clientService, ProductoService productService) {
        this.repo = historialRepository;
        this.clientService = clientService;
        this.productService = productService;
    }

    public List<Historial> findAll() {
        return (List<Historial>) repo.findAll();
    }

    public Historial findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public ResponseEntity<String> add(HistorialDTO historial) {
        Historial h = createHistorial(historial);
        Producto product = h.getProducto();

        switch (h.getTipo()) {
            case "Compra":
                if (product.getStock() < h.getCantidad()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Stock insuficiente: Solo quedan " + product.getStock() + " unidades disponibles");
                } else {
                    repo.save(h);

                    ProductoDTO productoDTO = createProduct(product);
                    productoDTO.setStock(product.getStock() - h.getCantidad());
                    productService.update(productoDTO, productoDTO.getId());

                    return ResponseEntity.status(HttpStatus.OK).body("Compra procesada corractamente");
                }

            case "Devolución":
                long remainDays = ChronoUnit.DAYS.between(h.getFechaCompra(), LocalDate.now());

                if (remainDays > 30) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Han pasado más de 30 días desde la compra");
                } else {
                    repo.save(h);

                    ProductoDTO productoDTO = createProduct(product);
                    productoDTO.setStock(product.getStock() + h.getCantidad());
                    productService.update(productoDTO, productoDTO.getId());

                    return ResponseEntity.status(HttpStatus.OK).body("Devolución procesada correctamente");
                }
            default:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Tipo de historial incorrecto");
        }
    }

    public ResponseEntity<String> update(HistorialDTO historial, int id) {
        Historial h = createHistorialWithID(historial, id);
        repo.save(h);
        return ResponseEntity.status(HttpStatus.OK).body("Se ha actualizado un nuevo historial");
    }

    public ResponseEntity<String> delete(int id) {
        Historial historial = repo.findById(id).orElse(null);
        repo.delete(historial);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Se ha eliminado un nuevo historial");
    }

    public Historial createHistorial(HistorialDTO historialDTO) {
        Historial h = new Historial();
        Cliente c = clientService.findById(historialDTO.getClientID());
        Producto p = productService.findById(historialDTO.getProductID());

        h.setCliente(c);
        h.setProducto(p);
        h.setFechaCompra(historialDTO.getPurchaseDate());
        h.setCantidad(historialDTO.getAmount());
        h.setTipo(historialDTO.getType());
        h.setDescripcion(historialDTO.getDescription());

        return h;
    }

    public Historial createHistorialWithID(HistorialDTO historialDTO, int id) {
        Historial h = new Historial();
        Cliente c = clientService.findById(historialDTO.getClientID());
        Producto p = productService.findById(historialDTO.getProductID());

        h.setCliente(c);
        h.setProducto(p);
        h.setFechaCompra(historialDTO.getPurchaseDate());
        h.setCantidad(historialDTO.getAmount());
        h.setTipo(historialDTO.getType());
        h.setDescripcion(historialDTO.getDescription());
        h.setId(id);

        return h;
    }

    public ProductoDTO createProduct(Producto product) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(product.getId());
        productoDTO.setName(product.getNombre());
        productoDTO.setDescription(product.getDescripcion());
        productoDTO.setPrice(product.getPrecio());
        productoDTO.setStock(product.getStock());

        return productoDTO;
    }
}
