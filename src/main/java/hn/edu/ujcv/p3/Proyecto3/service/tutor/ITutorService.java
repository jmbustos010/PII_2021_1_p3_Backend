package hn.edu.ujcv.p3.Proyecto3.service.tutor;

import hn.edu.ujcv.p3.Proyecto3.entity.Tutor;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface ITutorService {
    Tutor saveTutor(Tutor tutor)throws BusinessException;
    List<Tutor> saveTutores(List<Tutor> tutor) throws BusinessException;
    List<Tutor> getTutor() throws BusinessException;
    Tutor getTutorById(long id) throws BusinessException, NotFoundException;
    Tutor getTutorByNombre(String nombre)throws BusinessException, NotFoundException;
    void deleteTutor(long Id)throws BusinessException, NotFoundException;
    Tutor updateTutor (Tutor tutor)throws BusinessException, NotFoundException;
}

