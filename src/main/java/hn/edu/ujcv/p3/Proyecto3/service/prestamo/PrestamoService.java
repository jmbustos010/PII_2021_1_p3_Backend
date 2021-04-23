package hn.edu.ujcv.p3.Proyecto3.service.prestamo;

import hn.edu.ujcv.p3.Proyecto3.entity.Prestamo;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService implements IPrestamoService {
    @Autowired
    private PrestamoRepository repository;

    public Prestamo savePrestamo(Prestamo prestamo) throws BusinessException {
        try{
            if(String.valueOf(prestamo.getId()).length() > 10) {
                throw new Exception("Error! El Id se exede la cantidad de digitos permitidos(10)");
            }
            if(prestamo.getNombrelibro().isEmpty()){
                throw new Exception("Error! El nombre no puede estar vacio");
            }
            if(prestamo.getDescripcion().isEmpty()){
                throw new Exception("Error! La descripcion no puede estar vacia");
            }
            if(prestamo.getCondicion().isEmpty()){
                throw new Exception("Error! La condicion no puede estar vacio");
            }
            if(prestamo.getFechasalida().isEmpty()) {
                throw new Exception("Error! La fecha de salida no puede estar vacia");
            }
            if(prestamo.getFechaentrega().isEmpty()) {
                throw new Exception("Error! La fecha de entrega no puede estar vacia");
            }
            return repository.save(prestamo);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Prestamo> savePrestamos(List<Prestamo> prestamo) throws BusinessException{
        try{
            return repository.saveAll(prestamo);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Prestamo> getPrestamo() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public Prestamo getPrestamoById(long id) throws BusinessException, NotFoundException {
        Optional<Prestamo> opt = null;
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
    public Prestamo getPrestamoByName(String nombre)throws BusinessException, NotFoundException {
        Optional<Prestamo> opt = null;
        try{
            opt = repository.findByNombrelibro(nombre);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el prestamo " + nombre);
        }
        return opt.get();
    }
    public void deletePrestamo(long id)throws BusinessException, NotFoundException {
        Optional<Prestamo> opt = null;
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
    public Prestamo updatePrestamo(Prestamo prestamo)throws BusinessException,NotFoundException{
        Optional<Prestamo> opt;
        try{
            if(prestamo.getNombrelibro().isEmpty()){
                throw new Exception("Error! El nombre no puede estar vacio");
            }
            if(prestamo.getDescripcion().isEmpty()){
                throw new Exception("Error! La descripcion no puede estar vacia");
            }
            if(prestamo.getCondicion().isEmpty()){
                throw new Exception("Error! La condicion no puede estar vacio");
            }
            if(prestamo.getFechasalida().isEmpty()) {
                throw new Exception("Error! La fecha de salida no puede estar vacia");
            }
            if(prestamo.getFechaentrega().isEmpty()) {
                throw new Exception("Error! La fecha de entrega no puede estar vacia");
            }
            opt = repository.findById(prestamo.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el prestamo " + prestamo.getId());
        }
        else{
            try {
                Prestamo existingPrestamo = new Prestamo();
                existingPrestamo.setId(prestamo.getId());
                existingPrestamo.setNombrelibro(prestamo.getNombrelibro());
                existingPrestamo.setDescripcion(prestamo.getDescripcion());
                existingPrestamo.setCondicion(prestamo.getCondicion());
                existingPrestamo.setFechasalida(prestamo.getFechasalida());
                existingPrestamo.setFechaentrega(prestamo.getFechaentrega());
                return repository.save(existingPrestamo);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}

