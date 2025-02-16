package com.example.tiendaonlinespring.Repository;

import com.example.tiendaonlinespring.Modelo.Cliente;
import com.example.tiendaonlinespring.Modelo.Historial;
import com.example.tiendaonlinespring.Modelo.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialRepository extends CrudRepository<Historial, Integer> {

}
