package hn.edu.ujcv.p3.Proyecto3.repository;

import hn.edu.ujcv.p3.Proyecto3.entity.Tutoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutoriaRepository extends JpaRepository<Tutoria, Long> {
    Optional<Tutoria> findByMateria(String materia);
}
