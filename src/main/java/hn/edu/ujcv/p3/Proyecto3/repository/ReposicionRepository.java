package hn.edu.ujcv.p3.Proyecto3.repository;

import hn.edu.ujcv.p3.Proyecto3.entity.Reposicion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReposicionRepository extends JpaRepository<Reposicion, Long> {
    Optional<Reposicion> findByNumerocuentaalumno(long numerocuentaalumno);
}
