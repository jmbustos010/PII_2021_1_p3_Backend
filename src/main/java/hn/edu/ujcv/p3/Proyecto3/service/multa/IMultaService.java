package hn.edu.ujcv.p3.Proyecto3.service.multa;


import hn.edu.ujcv.p3.Proyecto3.entity.Multa;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface IMultaService {
    Multa saveMulta(Multa multa)throws BusinessException;
    List<Multa> saveMultas(List<Multa> multa) throws BusinessException;
    List<Multa> getMulta() throws BusinessException;
    Multa getMultaById(long id) throws BusinessException, NotFoundException;
    Multa getMultaByName(String nombre)throws BusinessException, NotFoundException;
    void deleteMulta(long Id)throws BusinessException, NotFoundException;
    Multa updateMulta (Multa multa)throws BusinessException, NotFoundException;
}
