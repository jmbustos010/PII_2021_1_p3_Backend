package hn.edu.ujcv.p3.Proyecto3.controller;

import hn.edu.ujcv.p3.Proyecto3.entity.Alumno;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.alumno.AlumnoService;
import hn.edu.ujcv.p3.Proyecto3.utils.Constants;
import hn.edu.ujcv.p3.Proyecto3.utils.RestApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService service;

    @PostMapping("/addAlumno")
    public ResponseEntity<Object> addAlumno(@RequestBody Alumno alumno) {
        try {
            service.saveAlumno(alumno);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_ALUMNO + alumno.getId());
            return new ResponseEntity(alumno, responseHeader, HttpStatus.CREATED);
        } catch (Exception e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//-----
    @PostMapping("/addAlumnos")
    public ResponseEntity<Object> addAlumnos(@RequestBody List<Alumno> alumnos) {
        try {
            return new ResponseEntity(service.saveAlumno(alumnos), HttpStatus.CREATED);
        } catch (Exception e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//-----
    @GetMapping("")
    public ResponseEntity<List<Alumno>> findAllAlumnos() {
        try {
            return new ResponseEntity(service.getAlumno(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//-----
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findAlumnoById(@PathVariable long id) {
        try {
            return new ResponseEntity(service.getAlumnoById(id), HttpStatus.OK);
        } catch (BusinessException e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
//-----
    @GetMapping("/name/{nombre}")
    public ResponseEntity<Object> findAlumnoByNombre(@PathVariable String nombre) {
        try {
            return new ResponseEntity(service.getAlumnoByName(nombre), HttpStatus.OK);
        } catch (BusinessException e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
//-----
    @PutMapping("")
    public ResponseEntity<Object> updateAlumno(@RequestBody Alumno alumno) {
        try {
            service.updateAlumno(alumno);
            return new ResponseEntity(alumno, HttpStatus.OK);
        } catch (BusinessException e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
//-----
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAlumno(@PathVariable long id) {
        try {
            service.deleteAlumno(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (BusinessException e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
