package hn.edu.ujcv.p3.Proyecto3.service.bibliotecario;


import hn.edu.ujcv.p3.Proyecto3.entity.Bibliotecario;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.BibliotecarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliotecarioService implements IBibliotecarioService{
    @Autowired
    private BibliotecarioRepository repository;

    public Bibliotecario saveBibliotecario(Bibliotecario bibliotecario) throws BusinessException {
        try{
            if (bibliotecario.getDireccion().isEmpty()){
                throw new BusinessException("El campo de la direccion está vacio");
            }
            if (bibliotecario.getNombre().isEmpty()){
                throw new BusinessException("El nombre del bibliotecario no puede estar vacio");
            }
            if (String.valueOf(bibliotecario.getId()).length() > 10){
                throw new BusinessException("El Id del bibliotecario no puede tener mas de 10 digitos");
            }
            if (String.valueOf(bibliotecario.getTelefono()).length() > 8){
                throw new BusinessException("El telefono del bibliotecario no puede tener mas de 8 digitos");
            }
            if (String.valueOf(bibliotecario.getTelefono()).length() < 8){
                throw new BusinessException("El telefono del bibliotecario no puede tener menos de 8 digitos");
            }
            if (String.valueOf(bibliotecario.getNumeroidentidad()).length() > 13){
                throw new BusinessException("El numero de identidad del bibliotecario no puede tener mas de 13 digitos");
            }
            if (bibliotecario.getId() <= 0){
                throw new BusinessException("El Id no puede ser menor o igual a 0");
            }

            return repository.save(bibliotecario);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public List<Bibliotecario> saveBibliotecario(List<Bibliotecario> bibliotecario) throws BusinessException{
        try{
            return repository.saveAll(bibliotecario);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public List<Bibliotecario> getBibliotecario() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //----
    public Bibliotecario getBibliotecarioById(long id) throws BusinessException, NotFoundException {
        Optional<Bibliotecario> opt = null;
        try{
            if (id <= 0){
                throw new BusinessException("El id que ingreso no es valido");
            }

            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Bibliotecario  " + id);
        }
        return opt.get();
    }
    //-----
    public Bibliotecario getBibliotecarioByName(String nombre)throws BusinessException, NotFoundException {
        Optional<Bibliotecario> opt = null;
        try{
            if (nombre.isEmpty()){
                throw new BusinessException("El nombre que ingreso esta vacio");
            }

            opt = repository.findByNombre(nombre);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Bibliotecario  " + nombre);
        }
        return opt.get();
    }
    //-----
    public void deleteBibliotecario(long id)throws BusinessException, NotFoundException {
        Optional<Bibliotecario> opt = null;
        try{
            if (id <= 0){
                throw new BusinessException("El id que ingreso no es valido");
            }

            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Bibliotecario  " + id);
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
    public Bibliotecario updateBibliotecario(Bibliotecario bibliotecario)throws BusinessException,NotFoundException{
        Optional<Bibliotecario> opt;
        try{
            opt = repository.findById(bibliotecario.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Bibliotecario " + bibliotecario.getId());
        }
        else{
            try {
                if (bibliotecario.getDireccion().isEmpty()){
                    throw new BusinessException("El campo de la direccion está vacio");
                }
                if (bibliotecario.getNombre().isEmpty()){
                    throw new BusinessException("El nombre del bibliotecario no puede estar vacio");
                }
                if (String.valueOf(bibliotecario.getId()).length() > 10){
                    throw new BusinessException("El Id del bibliotecario no puede tener mas de 10 digitos");
                }
                if (String.valueOf(bibliotecario.getTelefono()).length() > 8){
                    throw new BusinessException("El telefono del bibliotecario no puede tener mas de 8 digitos");
                }
                if (String.valueOf(bibliotecario.getTelefono()).length() < 8){
                    throw new BusinessException("El telefono del bibliotecario no puede tener menos de 8 digitos");
                }
                if (String.valueOf(bibliotecario.getNumeroidentidad()).length() > 13){
                    throw new BusinessException("El numero de identidad del bibliotecario no puede tener mas de 13 digitos");
                }

                Bibliotecario existingBibliotecario=new Bibliotecario();
                existingBibliotecario.setId(bibliotecario.getId());
                existingBibliotecario.setNombre(bibliotecario.getNombre());
                existingBibliotecario.setTelefono(bibliotecario.getTelefono());
                existingBibliotecario.setNumeroidentidad(bibliotecario.getNumeroidentidad());
                existingBibliotecario.setFechanacimiento(bibliotecario.getFechanacimiento());
                existingBibliotecario.setDireccion(bibliotecario.getDireccion());
                return repository.save(existingBibliotecario);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
