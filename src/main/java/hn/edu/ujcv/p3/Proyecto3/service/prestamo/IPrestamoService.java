package hn.edu.ujcv.p3.Proyecto3.service.prestamo;

import hn.edu.ujcv.p3.Proyecto3.entity.Prestamo;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface IPrestamoService {
    Prestamo savePrestamo(Prestamo prestamo)throws BusinessException;
    List<Prestamo> savePrestamos(List<Prestamo> prestamo) throws BusinessException;
    List<Prestamo> getPrestamo() throws BusinessException;
    Prestamo getPrestamoById(long id) throws BusinessException, NotFoundException;
    Prestamo getPrestamoByName(String nombrelibro)throws BusinessException, NotFoundException;
    void deletePrestamo(long id)throws BusinessException, NotFoundException;
    Prestamo updatePrestamo (Prestamo prestamo)throws BusinessException, NotFoundException;
}
