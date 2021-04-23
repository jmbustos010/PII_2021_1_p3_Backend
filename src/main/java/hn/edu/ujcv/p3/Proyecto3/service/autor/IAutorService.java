package hn.edu.ujcv.p3.Proyecto3.service.autor;


import hn.edu.ujcv.p3.Proyecto3.entity.Autor;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface IAutorService {
    Autor saveAutor(Autor autor)throws BusinessException;
    List<Autor> saveAutor(List<Autor> autoress) throws BusinessException;
    List<Autor> getAutor() throws BusinessException;
    Autor getAutorById(long id) throws BusinessException, NotFoundException;
    Autor getAutorByName(String nombre)throws BusinessException, NotFoundException;
    void deleteAutor(long Id)throws BusinessException, NotFoundException;
    Autor updateAutor (Autor autor)throws BusinessException, NotFoundException;
}
