package hn.edu.ujcv.p3.Proyecto3.service.autor;


import hn.edu.ujcv.p3.Proyecto3.entity.Autor;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Autor saveAutor(Autor autor) throws BusinessException {
        try{

            if (autor.getNombre().isEmpty()){
                throw new BusinessException("El nombre del autor no puede estar vacio");
            }
            if (autor.getNacionalidad().isEmpty()){
                throw new BusinessException("La nacionalidad del autor no puede estar vacia");
            }
            if (autor.getGeneroescritura().isEmpty()){
                throw new BusinessException("El geneno de escritura del autor no puede estar vacio");
            }
            if (String.valueOf(autor.getId()).length() > 10){
                throw new BusinessException("El Id del autor no puede tener mas de 10 digitos");
            }
            if (String.valueOf(autor.getNumeroidentidad()).length() > 13){
                throw new BusinessException("El numero de identidad del autor no puede tener mas de 13 digitos");
            }
            if (autor.getId() <= 0){
                throw new BusinessException("El Id no puede ser menor o igual a 1");
            }
            return repository.save(autor);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
//-----
    public List<Autor> saveAutor(List<Autor> autores) throws BusinessException{
        try{
            return repository.saveAll(autores);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    public List<Autor> getAutor() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
//-----
    public Autor getAutorById(long id) throws BusinessException, NotFoundException {
        Optional<Autor> opt = null;
        try{
            if (id <= 0){
                throw new BusinessException("El id que ingreso no es valido");
            }

            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Autor " + id);
        }
        return opt.get();
    }
//-----
    public Autor getAutorByName(String nombre)throws BusinessException, NotFoundException {
        Optional<Autor> opt = null;
        try{
            if (nombre.isEmpty()){
                throw new BusinessException("El nombre que ingreso esta vacio");
            }
            opt = repository.findByNombre(nombre);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Autor " + nombre);
        }
        return opt.get();
    }
//-----
    public void deleteAutor(long id)throws BusinessException, NotFoundException {
        Optional<Autor> opt = null;
        try{
            if (id <= 0){
                throw new BusinessException("El id que ingreso no es valido");
            }

            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Autor " + id);
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
    public Autor updateAutor(Autor autor)throws BusinessException,NotFoundException{
        Optional<Autor> opt;
        try{
            opt = repository.findById(autor.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Autor " + autor.getId());
        }
        else{
            try {
                if (autor.getNombre().isEmpty()){
                    throw new BusinessException("El nombre del autor no puede estar vacio");
                }
                if (autor.getNacionalidad().isEmpty()){
                    throw new BusinessException("La nacionalidad del autor no puede estar vacia");
                }
                if (autor.getGeneroescritura().isEmpty()){
                    throw new BusinessException("El geneno de escritura del autor no puede estar vacio");
                }
                if (String.valueOf(autor.getId()).length() > 10){
                    throw new BusinessException("El Id del autor no puede tener mas de 10 digitos");
                }
                if (String.valueOf(autor.getNumeroidentidad()).length() > 13){
                    throw new BusinessException("El numero de identidad del autor no puede tener mas de 13 digitos");
                }

                Autor existingAutor=new Autor();
                existingAutor.setId(autor.getId());
                existingAutor.setNombre(autor.getNombre());
                existingAutor.setNumeroidentidad(autor.getNumeroidentidad());
                existingAutor.setNacionalidad(autor.getNacionalidad());
                existingAutor.setGeneroescritura(autor.getGeneroescritura());
                existingAutor.setFechanacimiento(autor.getFechanacimiento());
                return repository.save(existingAutor);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
