package hn.edu.ujcv.p3.Proyecto3.service.libro;

import hn.edu.ujcv.p3.Proyecto3.entity.Libro;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface ILibroService {
    Libro saveLibro(Libro libro)throws BusinessException;
    List<Libro> saveLibro(List<Libro> libros) throws BusinessException;
    List<Libro> getLibro() throws BusinessException;
    Libro getLibroById(long id) throws BusinessException, NotFoundException;
    Libro getLibroByTitulo(String titulo)throws BusinessException, NotFoundException;
    void deleteLibro(long Id)throws BusinessException, NotFoundException;
    Libro updateLibro (Libro libro)throws BusinessException, NotFoundException;
}
