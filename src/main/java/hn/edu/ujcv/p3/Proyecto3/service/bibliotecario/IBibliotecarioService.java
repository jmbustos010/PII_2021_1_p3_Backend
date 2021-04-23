package hn.edu.ujcv.p3.Proyecto3.service.bibliotecario;

import hn.edu.ujcv.p3.Proyecto3.entity.Bibliotecario;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface IBibliotecarioService {
    Bibliotecario saveBibliotecario(Bibliotecario bibliotecario)throws BusinessException;
    List<Bibliotecario> saveBibliotecario(List<Bibliotecario> bibliotecario) throws BusinessException;
    List<Bibliotecario> getBibliotecario() throws BusinessException;
    Bibliotecario getBibliotecarioById(long id) throws BusinessException, NotFoundException;
    Bibliotecario getBibliotecarioByName(String nombre)throws BusinessException, NotFoundException;
    void deleteBibliotecario(long Id)throws BusinessException, NotFoundException;
    Bibliotecario updateBibliotecario (Bibliotecario bibliotecario)throws BusinessException, NotFoundException;
}
