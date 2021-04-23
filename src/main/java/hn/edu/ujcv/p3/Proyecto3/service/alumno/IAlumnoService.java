package hn.edu.ujcv.p3.Proyecto3.service.alumno;

import hn.edu.ujcv.p3.Proyecto3.entity.Alumno;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface IAlumnoService {
    Alumno saveAlumno(Alumno alumno)throws BusinessException;
    List<Alumno> saveAlumno(List<Alumno> alumno) throws BusinessException;
    List<Alumno> getAlumno() throws BusinessException;
    Alumno getAlumnoById(long id) throws BusinessException, NotFoundException;
    Alumno getAlumnoByName(String nombre)throws BusinessException, NotFoundException;
    void deleteAlumno(long Id)throws BusinessException, NotFoundException;
    Alumno updateAlumno (Alumno alumno)throws BusinessException, NotFoundException;
}
