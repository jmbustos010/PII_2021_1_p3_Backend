package hn.edu.ujcv.p3.Proyecto3.service.editorial;

import hn.edu.ujcv.p3.Proyecto3.entity.Editorial;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface IEditorialService {
    Editorial saveEditoriales(Editorial editorial)throws BusinessException;
    List<Editorial> saveEditoriales(List<Editorial> editorial) throws BusinessException;
    List<Editorial> getEditorial() throws BusinessException;
    Editorial getEditorialById(long id) throws BusinessException, NotFoundException;
    Editorial getEditorialByName(String nombre)throws BusinessException, NotFoundException;
    void deleteEditorial(long Id)throws BusinessException, NotFoundException;
    Editorial updateEditorial (Editorial editorial)throws BusinessException, NotFoundException;
}
