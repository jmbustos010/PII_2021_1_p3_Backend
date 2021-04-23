package hn.edu.ujcv.p3.Proyecto3.service.tutorias;

import hn.edu.ujcv.p3.Proyecto3.entity.Tutoria;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface ITutoriaService {

    Tutoria saveTutoria(Tutoria tutoria) throws BusinessException;
    List<Tutoria> saveTutorias(List<Tutoria> tutoria) throws BusinessException;
    List<Tutoria> getTutoria() throws BusinessException;
    Tutoria getTutoriaById(long id) throws BusinessException, NotFoundException;
    Tutoria getTutoriaByMateria(String nombre)throws BusinessException, NotFoundException;
    void deleteTutoria(long Id)throws BusinessException, NotFoundException;
    Tutoria updateTutoria (Tutoria tutoria)throws BusinessException, NotFoundException;


}
