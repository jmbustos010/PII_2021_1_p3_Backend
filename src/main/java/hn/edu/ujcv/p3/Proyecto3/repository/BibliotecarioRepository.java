package hn.edu.ujcv.p3.Proyecto3.repository;

import hn.edu.ujcv.p3.Proyecto3.entity.Bibliotecario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Long> {
    Optional<Bibliotecario> findByNombre(String nombre);
}
