package hn.edu.ujcv.p3.Proyecto3.service.reposicion;

import hn.edu.ujcv.p3.Proyecto3.entity.Reposicion;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.ReposicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReposicionService implements IReposicionService {
    @Autowired
    private ReposicionRepository repository;

    public Reposicion saveReposicion(Reposicion reposicion) throws BusinessException {
        try{
            if(String.valueOf(reposicion.getId()).length() > 10) {
                throw new Exception("Error! El Id se exede la cantidad de digitos permitidos(10)");
            }
            if(String.valueOf(reposicion.getId()).length() < 0) {
                throw new Exception("Error! El Id no puede ser menor a 0");
            }
            if(reposicion.getNombrealumno().isEmpty()){
                throw new Exception("Error! El nombre del alumno no puede estar vacio");
            }
            if(String.valueOf(reposicion.getNumerocuentaalumno()).length() > 12){
                throw new Exception("Error! El numero de cuenta exede la cantidad de digitos permitidos(12)");
            }
            if(String.valueOf(reposicion.getNumerocuentaalumno()).length() < 12){
                throw new Exception("Error! El numero de cuenta no puede ser menor a la cantidad de digitos permitidos(12)");
            }
            if(reposicion.getNombrelibro().isEmpty()){
                throw new Exception("Error! El nombre del libro no puede estar vacio");
            }
            if(reposicion.getMotivo().isEmpty()) {
                throw new Exception("Error! El motivo no puede estar vacio");
            }
            if(reposicion.getFechareposicion().isEmpty()) {
                throw new Exception("Error! La fecha de reposicion no puede estar vacia");
            }
            return repository.save(reposicion);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Reposicion> saveReposiciones(List<Reposicion> reposicion) throws BusinessException{
        try{
            return repository.saveAll(reposicion);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Reposicion> getReposicion() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public Reposicion getReposicionById(long id) throws BusinessException, NotFoundException {
        Optional<Reposicion> opt = null;
        try{
            if(id <=0){
                throw new BusinessException("El id ingresado no es valido");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el prestamo " + id);
        }
        return opt.get();
    }
    public Reposicion getReposicionByNumerocuentaalumno(long numerocuentaalumno)throws BusinessException, NotFoundException {
        Optional<Reposicion> opt = null;
        try{
            if(numerocuentaalumno <=0){
                throw new BusinessException("El numero de cuenta del alumno ingresado no es valido");
            }
            opt = repository.findByNumerocuentaalumno(numerocuentaalumno);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el prestamo " + numerocuentaalumno);
        }
        return opt.get();
    }
    public void deleteReposicion(long id)throws BusinessException, NotFoundException {
        Optional<Reposicion> opt = null;
        try{
            if(id <=0){
                throw new BusinessException("El id ingresado no es valido");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el prestamo " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Reposicion updateReposicion(Reposicion reposicion)throws BusinessException,NotFoundException{
        Optional<Reposicion> opt;
        try{
            if(reposicion.getNombrealumno().isEmpty()){
                throw new Exception("Error! El nombre del alumno no puede estar vacio");
            }
            if(String.valueOf(reposicion.getNumerocuentaalumno()).length() > 12){
                throw new Exception("Error! El numero de cuenta exede la cantidad de digitos permitidos(12)");
            }
            if(String.valueOf(reposicion.getNumerocuentaalumno()).length() < 12){
                throw new Exception("Error! El numero de cuenta no puede ser menor a la cantidad de digitos permitidos(12)");
            }
            if(reposicion.getNombrelibro().isEmpty()){
                throw new Exception("Error! El nombre del libro no puede estar vacio");
            }
            if(reposicion.getMotivo().isEmpty()) {
                throw new Exception("Error! El motivo no puede estar vacio");
            }
            if(reposicion.getFechareposicion().isEmpty()) {
                throw new Exception("Error! La fecha de reposicion no puede estar vacia");
            }
            opt = repository.findById(reposicion.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el prestamo " + reposicion.getId());
        }
        else{
            try {
                Reposicion existingReposicion = new Reposicion();
                existingReposicion.setId(reposicion.getId());
                existingReposicion.setNombrealumno(reposicion.getNombrealumno());
                existingReposicion.setNumerocuentaalumno(reposicion.getNumerocuentaalumno());
                existingReposicion.setNombrelibro(reposicion.getNombrelibro());
                existingReposicion.setMotivo(reposicion.getMotivo());
                existingReposicion.setPrecio(reposicion.getPrecio());
                existingReposicion.setFechareposicion(reposicion.getFechareposicion());
                return repository.save(existingReposicion);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
