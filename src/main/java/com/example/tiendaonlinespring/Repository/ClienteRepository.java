package com.example.tiendaonlinespring.Repository;

import com.example.tiendaonlinespring.Modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
