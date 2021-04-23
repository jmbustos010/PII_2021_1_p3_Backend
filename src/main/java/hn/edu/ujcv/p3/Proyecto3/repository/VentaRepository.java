package hn.edu.ujcv.p3.Proyecto3.repository;


import hn.edu.ujcv.p3.Proyecto3.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    Optional<Venta> findByIdalumno(long idalumno);
}