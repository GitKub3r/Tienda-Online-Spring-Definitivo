package com.example.tiendaonlinespring.Repository;

import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Modelo.Historial;
import com.example.tiendaonlinespring.Modelo.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistorialRepository extends CrudRepository<Historial, Integer> {

}
