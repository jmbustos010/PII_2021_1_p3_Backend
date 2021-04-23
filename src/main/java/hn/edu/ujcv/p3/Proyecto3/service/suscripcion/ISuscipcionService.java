package hn.edu.ujcv.p3.Proyecto3.service.suscripcion;

import hn.edu.ujcv.p3.Proyecto3.entity.Suscripcion;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface ISuscipcionService {
    Suscripcion saveSuscripcion(Suscripcion suscripcion)throws BusinessException;
    List<Suscripcion> saveSuscripciones(List<Suscripcion> suscripciones) throws BusinessException;
    List<Suscripcion> getSuscripcion() throws BusinessException;
    Suscripcion getSuscripcionById(long id) throws BusinessException, NotFoundException;
    Suscripcion getSuscripcionByNombrealumno(String nombrealumno)throws BusinessException, NotFoundException;
    void deleteSuscripcion(long Id)throws BusinessException, NotFoundException;
    Suscripcion updateSuscripcion (Suscripcion suscripcion)throws BusinessException, NotFoundException;

}
