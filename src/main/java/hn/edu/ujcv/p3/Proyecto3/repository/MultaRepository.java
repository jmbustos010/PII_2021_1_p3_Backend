package hn.edu.ujcv.p3.Proyecto3.repository;

import hn.edu.ujcv.p3.Proyecto3.entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MultaRepository extends JpaRepository<Multa, Long> {
    Optional<Multa> findByNombre(String nombre);
}
