package hn.edu.ujcv.p3.Proyecto3.service.tutor;

import hn.edu.ujcv.p3.Proyecto3.entity.Tutor;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService implements ITutorService{
    @Autowired
    private TutorRepository repository;

    public Tutor saveTutor(Tutor tutor) throws BusinessException {
        try{
            if (tutor.getNombre().isEmpty()) {
                throw new Exception("Error: el nombre no debe estar vacío");
            }
            if (tutor.getId() <= 0) {
                throw new Exception("Error: El id no puede ser menor o igual a 0");
            }
            if (tutor.getFechanacimiento().isEmpty()) {
                throw new Exception("Error: Fecha incorrecta");
            }
            if (tutor.getClaseimpartida().isEmpty()) {
                throw new Exception("Error: La clase impartida no puede estar vacia");
            }
            if (String.valueOf(tutor.getNumeroidentidad()).length() >13) {
                throw new Exception("Error: El numero de identidad  no puede tener mas de 13 digitos");
            }
            if (String.valueOf(tutor.getTelefono()).length()>8) {
                throw new Exception("Error: El telefono no puede tener mas de 8 digitos");
            }
            return repository.save(tutor);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    public List<Tutor> saveTutores(List<Tutor> tutor) throws BusinessException{
        try{
            return repository.saveAll(tutor);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Tutor> getTutor() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public Tutor getTutorById(long id) throws BusinessException, NotFoundException {
        Optional<Tutor> opt = null;
        try{
            if (id <= 0) {
                throw new Exception("Error: El id no debe ser menor o igual a 0");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el tutor con id " + id);
        }
        return opt.get();
    }
    public Tutor getTutorByNombre(String nombre)throws BusinessException, NotFoundException {
        Optional<Tutor> opt = null;
        try{
            if (nombre.isEmpty()) {
                throw new Exception("Error: el nombre no debe estar vacío");
            }
            opt = repository.findByNombre(nombre);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el tutor " + nombre);
        }
        return opt.get();
    }
    public void deleteTutor(long id)throws BusinessException, NotFoundException {
        Optional<Tutor> opt = null;
        try{
            if (id <= 0) {
                throw new Exception("Error: El id no debe ser menor o igual a 0");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el tutor con el id " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Tutor updateTutor(Tutor tutor)throws BusinessException,NotFoundException{
        Optional<Tutor> opt;
        try{
            if (tutor.getNombre().isEmpty()) {
                throw new Exception("Error: el nombre no debe estar vacío");
            }
            if (tutor.getFechanacimiento().toString().isEmpty()) {
                throw new Exception("Error: Fecha incorrecta");
            }
            if (tutor.getClaseimpartida().isEmpty()) {
                throw new Exception("Error: La clase impartida no puede estar vacia");
            }
            if (String.valueOf(tutor.getNumeroidentidad()).length() >13) {
                throw new Exception("Error: El numero de identidad  no puede tener mas de 13 digitos");
            }
            if (String.valueOf(tutor.getTelefono()).length()>8) {
                throw new Exception("Error: El telefono no puede tener mas de 8 digitos");
            }
            opt = repository.findById(tutor.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el tutor con el id " + tutor.getId());
        }
        else{
            try {
                Tutor existingTutor = new Tutor();
                existingTutor.setId(tutor.getId());
                existingTutor.setNombre(tutor.getNombre());
                existingTutor.setFechanacimiento(tutor.getFechanacimiento());
                existingTutor.setNumeroidentidad(tutor.getNumeroidentidad());
                existingTutor.setTelefono(tutor.getTelefono());
                existingTutor.setClaseimpartida(tutor.getClaseimpartida());
                return repository.save(existingTutor);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }

}
