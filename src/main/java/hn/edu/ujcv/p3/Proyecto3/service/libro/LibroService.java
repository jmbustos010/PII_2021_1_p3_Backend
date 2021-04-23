package hn.edu.ujcv.p3.Proyecto3.service.libro;


import hn.edu.ujcv.p3.Proyecto3.entity.Libro;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    public Libro saveLibro(Libro libro) throws BusinessException {
        try{
            if (libro.getTitulo().isEmpty()){
                throw new BusinessException("El titulo dle libro no puede estar vacio");
            }
            if (libro.getNombreautor().isEmpty()){
                throw new BusinessException("El nombre del autor de libro no puede estar vacio");
            }
            if (libro.getGenero().isEmpty()){
                throw new BusinessException("El genero del libro esta vacio");
            }
            if (libro.getIdioma().isEmpty()){
                throw new BusinessException("El idioma del libro esta vacio");
            }
            if (String.valueOf(libro.getId()).length() > 10){
                throw new BusinessException("El Id del bibliotecario no puede tener mas de 10 digitos");
            }
            if (libro.getId() <= 0){
                throw new BusinessException("El Id no puede ser menor o igual a 0");
            }

            return repository.save(libro);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public List<Libro> saveLibros(List<Libro> libro) throws BusinessException{
        try{
            return repository.saveAll(libro);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public List<Libro> getLibro() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public Libro getLibroById(long id) throws BusinessException, NotFoundException {
        Optional<Libro> opt = null;
        try{
            if (id <= 0){
                throw new BusinessException("El id que ingreso no es valido");
            }

            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Libro  " + id);
        }
        return opt.get();
    }
    //-----
    public Libro getLibroByTitulo(String titulo)throws BusinessException, NotFoundException {
        Optional<Libro> opt = null;
        try{
            if (titulo.isEmpty()){
                throw new BusinessException("El titulo que ingreso esta vacio");
            }

            opt = repository.findBytitulo(titulo);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Libro  " + titulo);
        }
        return opt.get();
    }
    //-----
    public void deleteLibro(long id)throws BusinessException, NotFoundException {
        Optional<Libro> opt = null;
        try{
            if (id <= 0){
                throw new BusinessException("El id que ingreso no es valido");
            }

            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Libro  " + id);
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
    public Libro updateLibro(Libro libro)throws BusinessException,NotFoundException{
        Optional<Libro> opt;
        try{
            opt = repository.findById(libro.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Libro " + libro.getId());
        }
        else{
            try {
                if (libro.getTitulo().isEmpty()){
                    throw new BusinessException("El titulo dle libro no puede estar vacio");
                }
                if (libro.getNombreautor().isEmpty()){
                    throw new BusinessException("El nombre del autor de libro no puede estar vacio");
                }
                if (libro.getGenero().isEmpty()){
                    throw new BusinessException("El genero del libro esta vacio");
                }
                if (libro.getIdioma().isEmpty()){
                    throw new BusinessException("El idioma del libro esta vacio");
                }
                if (String.valueOf(libro.getId()).length() > 10){
                    throw new BusinessException("El Id del bibliotecario no puede tener mas de 10 digitos");
                }
                if (libro.getId() <= 0){
                    throw new BusinessException("El Id no puede ser menor o igual a 1");
                }


                Libro existingLibro=new Libro();
                existingLibro.setId(libro.getId());
                existingLibro.setTitulo(libro.getTitulo());
                existingLibro.setNombreautor(libro.getNombreautor());
                existingLibro.setGenero(libro.getGenero());
                existingLibro.setIdioma(libro.getIdioma());
                existingLibro.setFechapublicacion(libro.getFechapublicacion());
                return repository.save(existingLibro);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
