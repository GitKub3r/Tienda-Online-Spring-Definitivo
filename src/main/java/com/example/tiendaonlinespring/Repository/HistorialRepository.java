package com.example.tiendaonlinespring.Repository;

import com.example.tiendaonlinespring.Modelo.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Integer> {

}
