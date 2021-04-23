package hn.edu.ujcv.p3.Proyecto3.service.reposicion;

import hn.edu.ujcv.p3.Proyecto3.entity.Reposicion;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface IReposicionService {
    Reposicion saveReposicion(Reposicion reposicion)throws BusinessException;
    List<Reposicion> saveReposiciones(List<Reposicion> reposicion) throws BusinessException;
    List<Reposicion> getReposicion() throws BusinessException;
    Reposicion getReposicionById(long id) throws BusinessException, NotFoundException;
    Reposicion getReposicionByNumerocuentaalumno(long numerocuentaalumno)throws BusinessException, NotFoundException;
    void deleteReposicion(long id)throws BusinessException, NotFoundException;
    Reposicion updateReposicion (Reposicion reposicion)throws BusinessException, NotFoundException;
}
