package hn.edu.ujcv.p3.Proyecto3.service.editorial;

import hn.edu.ujcv.p3.Proyecto3.entity.Editorial;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService implements IEditorialService{
    @Autowired
    private EditorialRepository repository;

    public Editorial saveEditoriales(Editorial editorial) throws BusinessException {
        try{
            if(String.valueOf(editorial.getId()).length() > 10){
                throw new Exception("Error! El Id se exede la cantidad de digitos permitidos(10)");
            }
            if(String.valueOf(editorial.getId()).length() <=0){
                throw new BusinessException("El Id ingresado no es valido");
            }
            if(editorial.getNombre().isEmpty()){
                throw new Exception("Error! El nombre no puede estar vacio");
            }
            if(editorial.getUbicacion().isEmpty()){
                throw new Exception("Error! La ubicacion no puede estar vacia");
            }
            if(editorial.getCorreo().isEmpty()){
                throw new Exception("Error! El correo no puede estar vacio");
            }
            if(String.valueOf(editorial.getTelefono()).length()  > 8) {
                throw new Exception("Error! El telefono no puede tener mas de 8 digitos");
            }
            if(String.valueOf(editorial.getTelefono()).length()  < 8) {
                throw new Exception("Error! El telefono no puede tener menos de 8 digitos");
            }
            if(editorial.getFechafundacion().isEmpty()) {
                throw new Exception("Error! La fecha no puede estar vacia");
            }
            return repository.save(editorial);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Editorial> saveEditoriales(List<Editorial> editorial) throws BusinessException{
        try{
            return repository.saveAll(editorial);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Editorial> getEditorial() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public Editorial getEditorialById(long id) throws BusinessException, NotFoundException {
        Optional<Editorial> opt = null;
        try{
            if(id <=0){
                throw new BusinessException("El Id ingresado no es valido");
            }
            if(String.valueOf(id).length() > 10){
                throw new Exception("Error! El Id se exede la cantidad de digitos permitidos(10)");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la Editorial " + id);
        }
        return opt.get();
    }
    public Editorial getEditorialByName(String nombre)throws BusinessException, NotFoundException {
        Optional<Editorial> opt = null;
        try{
            opt = repository.findByNombre(nombre);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la Editorial " + nombre);
        }
        return opt.get();
    }
    public void deleteEditorial(long id)throws BusinessException, NotFoundException {
        Optional<Editorial> opt = null;
        try{
            if(id <=0){
                throw new BusinessException("El id ingresado no es valido");
            }
            if(String.valueOf(id).length() > 10){
                throw new Exception("Error! El Id se exede la cantidad de digitos permitidos(10)");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la Editorial " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Editorial updateEditorial(Editorial editorial)throws BusinessException,NotFoundException{
        Optional<Editorial> opt;
        try{
            if(editorial.getNombre().isEmpty()){
                throw new Exception("Error! El nombre no puede estar vacio");
            }
            if(editorial.getUbicacion().isEmpty()){
                throw new Exception("Error! La ubicacion no puede estar vacia");
            }
            if(editorial.getCorreo().isEmpty()){
                throw new Exception("Error! El correo no puede estar vacio");
            }
            if(String.valueOf(editorial.getTelefono()).length()  > 8) {
                throw new Exception("Error! El telefono no puede tener mas de 8 digitos");
            }
            if(String.valueOf(editorial.getTelefono()).length()  < 8) {
                throw new Exception("Error! El telefono no puede tener menos de 8 digitos");
            }
            if(editorial.getFechafundacion().isEmpty()) {
                throw new Exception("Error! La fecha no puede estar vacia");
            }
            opt = repository.findById(editorial.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el producto " + editorial.getId());
        }
        else{
            try {
                Editorial existingEditorial=new Editorial();
                existingEditorial.setId(editorial.getId());
                existingEditorial.setNombre(editorial.getNombre());
                existingEditorial.setUbicacion(editorial.getUbicacion());
                existingEditorial.setCorreo(editorial.getCorreo());
                existingEditorial.setTelefono(editorial.getTelefono());
                existingEditorial.setFechafundacion(editorial.getFechafundacion());
                return repository.save(existingEditorial);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
