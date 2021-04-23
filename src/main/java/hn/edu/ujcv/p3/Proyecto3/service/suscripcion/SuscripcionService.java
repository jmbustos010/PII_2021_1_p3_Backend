package hn.edu.ujcv.p3.Proyecto3.service.suscripcion;

import hn.edu.ujcv.p3.Proyecto3.entity.Suscripcion;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.SuscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuscripcionService implements ISuscipcionService{
    @Autowired
    private SuscripcionRepository repository;

    public Suscripcion saveSuscripcion(Suscripcion suscripcion) throws BusinessException {
        try {
            if (suscripcion.getId() <= 0){
                throw new BusinessException("Error: El id no puede ser menor o igual a 0");
            }
            if (suscripcion.getTiposuscripcion().isEmpty()) {
                throw new BusinessException("Error: EL tipo de suscripcion no puede estar vacia");
            }
            if (suscripcion.getFechainicio().toString().isEmpty()){
                throw new BusinessException("Error: Fecha invalida");
            }
            if (suscripcion.getFechacierre().toString().isEmpty()) {
                throw new BusinessException("Error: Fecha invalida");
            }
            if (suscripcion.getNombrealumno().isEmpty()) {
                throw new BusinessException("Error: El nombre del alumno no puede estar vacio");
            }
            if (suscripcion.getPrecio()<=0){
                throw new BusinessException("Error: el precio no puede ser menor a o igual a 0");
            }
            return repository.save(suscripcion);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public List<Suscripcion> saveSuscripciones(List<Suscripcion> suscripcion) throws BusinessException {
        try {

            return repository.saveAll(suscripcion);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public List<Suscripcion> getSuscripcion() throws BusinessException {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public Suscripcion getSuscripcionById(long id) throws BusinessException, NotFoundException {
        Optional<Suscripcion> opt = null;
        try {
            if (id <= 0) {
                throw new BusinessException("Error: El id no puede ser menor o igual a 0");
            }
            opt = repository.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la suscripcion con el id " + id);
        }
        return opt.get();
    }

    public Suscripcion getSuscripcionByNombrealumno(String nombrealumno) throws BusinessException, NotFoundException {
        Optional<Suscripcion> opt = null;
        try {
            if (nombrealumno.isEmpty()){
                throw new BusinessException("Error: El nombre no puede estar vacio");
            }
            opt = repository.findByNombrealumno(nombrealumno);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("El alumno " + nombrealumno + " no posee suscripcion");
        }
        return opt.get();
    }

    public void deleteSuscripcion(long id) throws BusinessException, NotFoundException {
        Optional<Suscripcion> opt = null;
        try {
            if (id <= 0){
                throw new BusinessException("Error: El id no puede ser menor o igual a 0");
            }
            opt = repository.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la suscripcion con id " + id);
        } else {
            try {
                repository.deleteById(id);
            } catch (Exception e1) {
                throw new BusinessException(e1.getMessage());
            }
        }
    }

    public Suscripcion updateSuscripcion(Suscripcion suscripcion) throws BusinessException, NotFoundException {
        Optional<Suscripcion> opt;
        try {
            if (suscripcion.getId() <= 0){
                throw new BusinessException("Error: El id no puede ser menor o igual a 0");
            }
            if (suscripcion.getTiposuscripcion().isEmpty()) {
                throw new BusinessException("Error: EL tipo de suscripcion no puede estar vacia");
            }
            if (suscripcion.getFechainicio().isEmpty()){
                throw new BusinessException("Error: Fecha invalida");
            }
            if (suscripcion.getFechacierre().isEmpty()) {
                throw new BusinessException("Error: Fecha invalida");
            }
            if (suscripcion.getNombrealumno().isEmpty()) {
                throw new BusinessException("Error: El nombre del alumno no puede estar vacio");
            }
            if (suscripcion.getPrecio()<=0){
                throw new BusinessException("Error: el precio no puede ser menor a o igual a 0");
            }
            opt = repository.findById(suscripcion.getId());
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la suscripcion con el id " + suscripcion.getId());
        } else {
            try {
                Suscripcion existingSuscripcion = new Suscripcion();
                existingSuscripcion.setId(suscripcion.getId());
                existingSuscripcion.setTiposuscripcion(suscripcion.getTiposuscripcion());
                existingSuscripcion.setFechainicio(suscripcion.getFechainicio());
                existingSuscripcion.setFechacierre(suscripcion.getFechacierre());
                existingSuscripcion.setNombrealumno(suscripcion.getNombrealumno());
                existingSuscripcion.setPrecio(suscripcion.getPrecio());
                return repository.save(existingSuscripcion);
            } catch (Exception e1) {
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
