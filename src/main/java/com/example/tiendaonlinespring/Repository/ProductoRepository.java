package com.example.tiendaonlinespring.Repository;

import com.example.tiendaonlinespring.Modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
