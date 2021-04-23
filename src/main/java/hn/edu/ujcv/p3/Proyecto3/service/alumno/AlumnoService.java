package hn.edu.ujcv.p3.Proyecto3.service.alumno;

import hn.edu.ujcv.p3.Proyecto3.entity.Alumno;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService implements IAlumnoService {
    @Autowired
    private AlumnoRepository repository;

    public Alumno saveAlumno(Alumno alumno) throws BusinessException {
        try{
            if (alumno.getId() <= 0){
                throw new BusinessException("El Id no puede ser menor o igual a 0");
            }
            if (alumno.getNombre().isEmpty()){
                throw new BusinessException("El nombre del alumno no puede estar vacio");
            }
            if (alumno.getCarrera().isEmpty()){
                throw new BusinessException("El campo de la carrera está vacio");
            }
            if (String.valueOf(alumno.getId()).length() > 10){
                throw new BusinessException("El Id del alumno no puede tener mas de 10 digitos");
            }
            if (String.valueOf(alumno.getTelefono()).length() > 8){
                throw new BusinessException("El telefono del alumno no puede tener mas de 8 digitos");
            }
            if (String.valueOf(alumno.getTelefono()).length() < 8){
                throw new BusinessException("El telefono del alumno no puede tener menos de 8 digitos");
            }
            if (String.valueOf(alumno.getNumeroidentidad()).length() > 13){
                throw new BusinessException("El numero de identidad del alumno no puede tener mas de 13 digitos");
            }


            return repository.save(alumno);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public List<Alumno> saveAlumno(List<Alumno> alumno) throws BusinessException{
        try{
            return repository.saveAll(alumno);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public List<Alumno> getAlumno() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public Alumno getAlumnoById(long id) throws BusinessException, NotFoundException {
        Optional<Alumno> opt = null;
        try{
            if (id <= 0){
                throw new BusinessException("El id que ingreso no es valido");
            }

            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Alumno " + id);
        }
        return opt.get();
    }
    //-----
    public Alumno getAlumnoByName(String nombre)throws BusinessException, NotFoundException {
        Optional<Alumno> opt = null;
        try{
            if (nombre.isEmpty()){
                throw new BusinessException("El nombre que ingreso esta vacio");
            }

            opt = repository.findByNombre(nombre);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Alumno " + nombre);
        }
        return opt.get();
    }
    //-----
    public void deleteAlumno(long id)throws BusinessException, NotFoundException {
        Optional<Alumno> opt = null;
        try{
            if (id <= 0){
                throw new BusinessException("El id que ingreso no es valido");
            }

            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Alumno " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    //-----
    public Alumno updateAlumno(Alumno alumno)throws BusinessException,NotFoundException{
        Optional<Alumno> opt;
        try{
            opt = repository.findById(alumno.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Alumno " + alumno.getId());
        }
        else{
            try {
                if (alumno.getCarrera().isEmpty()){
                    throw new BusinessException("El campo de la carrera está vacio");
                }
                if (alumno.getNombre().isEmpty()){
                    throw new BusinessException("El nombre del alumno no puede estar vacio");
                }
                if (String.valueOf(alumno.getId()).length() > 10){
                    throw new BusinessException("El Id del alumno no puede tener mas de 10 digitos");
                }
                if (String.valueOf(alumno.getTelefono()).length() > 8){
                    throw new BusinessException("El telefono del alumno no puede tener mas de 8 digitos");
                }
                if (String.valueOf(alumno.getTelefono()).length() < 8){
                    throw new BusinessException("El telefono del alumno no puede tener menos de 8 digitos");
                }
                if (String.valueOf(alumno.getNumeroidentidad()).length() > 13){
                    throw new BusinessException("El numero de identidad del alumno no puede tener mas de 13 digitos");
                }

                Alumno existingAlumno=new Alumno();
                existingAlumno.setId(alumno.getId());
                existingAlumno.setNombre(alumno.getNombre());
                existingAlumno.setTelefono(alumno.getTelefono());
                existingAlumno.setNumeroidentidad(alumno.getNumeroidentidad());
                existingAlumno.setFechanacimiento(alumno.getFechanacimiento());
                existingAlumno.setCarrera(alumno.getCarrera());
                return repository.save(existingAlumno);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
