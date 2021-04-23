package hn.edu.ujcv.p3.Proyecto3.service.tutorias;


import hn.edu.ujcv.p3.Proyecto3.entity.Tutoria;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.TutoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutoriaService implements ITutoriaService {
    @Autowired
    private TutoriaRepository repository;
    public Tutoria saveTutoria(Tutoria tutoria) throws BusinessException {
        try{
            if (tutoria.getNombretutor().isEmpty()) {
                throw new Exception("Error: el nombre no debe estar vacío");
            }
            if (tutoria.getMateria().isEmpty()) {
                throw new Exception("Error: La materia no debe estar vacia");
            }
            if (tutoria.getHorario().isEmpty()) {
                throw new Exception("Error: Horario incorrecto");
            }
            if (tutoria.getUbicacion().isEmpty()) {
                throw new Exception("Error: La ubciacion no puede estar vacia");
            }
            if (tutoria.getPrecio() <= 0) {
                throw new Exception("Error: El precio no puede ser menor o igua la 0");
            }
            if (tutoria.getId() <= 0) {
                throw new Exception("Error: El id no puede ser menor o igual a 0");
            }
            return repository.save(tutoria);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Tutoria> saveTutorias(List<Tutoria> tutorias) throws BusinessException{
        try{
            return repository.saveAll(tutorias);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Tutoria> getTutoria() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public Tutoria getTutoriaById(long id) throws BusinessException, NotFoundException {
        Optional<Tutoria> opt = null;
        try{
            if (id <= 0) {
                throw new Exception("Error: El id no puede ser menor o igual a 0");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la tutoria con el id " + id);
        }
        return opt.get();
    }
    public Tutoria getTutoriaByMateria(String materia)throws BusinessException, NotFoundException {
        Optional<Tutoria> opt = null;
        try{
            if (materia.isEmpty()) {
                throw new Exception("Error: La materia no debe estar vacia");
            }
            opt = repository.findByMateria(materia);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la materia " + materia);
        }
        return opt.get();
    }
    public void deleteTutoria(long id)throws BusinessException, NotFoundException {
        Optional<Tutoria> opt = null;
        try{
            if (id <= 0) {
                throw new Exception("Error: El id no puede ser menor o igual a 0");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la tutoria con el id " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }

    public Tutoria updateTutoria(Tutoria tutoria)throws BusinessException,NotFoundException{
        Optional<Tutoria> opt;
        try{
            if (tutoria.getNombretutor().isEmpty()) {
                throw new Exception("Error: el nombre no debe estar vacío");
            }
            if (tutoria.getMateria().isEmpty()) {
                throw new Exception("Error: La materia no debe estar vacia");
            }
            if (tutoria.getHorario().isEmpty()) {
                throw new Exception("Error: Horario incorrecto");
            }
            if (tutoria.getUbicacion().isEmpty()) {
                throw new Exception("Error: La ubciacion no puede estar vacia");
            }
            if (tutoria.getPrecio() <= 0) {
                throw new Exception("Error: El precio no puede ser menor o igua la 0");
            }
            if (tutoria.getId() <= 0) {
                throw new Exception("Error: El id no puede ser menor o igual a 0");
            }
            opt = repository.findById(tutoria.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la tutoria con el id " + tutoria.getId());
        }
        else{
            try {
                Tutoria existingTutoria = new Tutoria();
                existingTutoria.setId(tutoria.getId());
                existingTutoria.setNombretutor(tutoria.getNombretutor());
                existingTutoria.setMateria(tutoria.getMateria());
                existingTutoria.setHorario(tutoria.getHorario());
                existingTutoria.setUbicacion(tutoria.getUbicacion());
                existingTutoria.setPrecio(tutoria.getPrecio());
                return repository.save(existingTutoria);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }










}
