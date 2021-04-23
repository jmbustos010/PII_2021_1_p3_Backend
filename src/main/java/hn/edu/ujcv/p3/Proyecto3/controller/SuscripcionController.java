package hn.edu.ujcv.p3.Proyecto3.controller;

import hn.edu.ujcv.p3.Proyecto3.entity.Editorial;
import hn.edu.ujcv.p3.Proyecto3.entity.Suscripcion;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.suscripcion.SuscripcionService;
import hn.edu.ujcv.p3.Proyecto3.utils.Constants;
import hn.edu.ujcv.p3.Proyecto3.utils.RestApiError;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Suscripcion")
public class SuscripcionController {
    @Autowired
    private SuscripcionService service;

    @PostMapping("/addSuscripcion")
    public ResponseEntity<Object> addSuscripcion(@RequestBody Suscripcion suscripcion){
        try{
            service.saveSuscripcion(suscripcion);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_SUSCRIPCION + suscripcion.getId());
            return new ResponseEntity<>(suscripcion,responseHeader, HttpStatus.CREATED);
        }
        catch (Exception e){
            //return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addSuscripciones")
    public ResponseEntity<Any> addSuscripciones(@RequestBody List<Suscripcion> suscripciones){
        try{
            return new ResponseEntity(service.saveSuscripciones(suscripciones), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Suscripcion>> findAllSuscripcion(){
        try{
            return new ResponseEntity<>(service.getSuscripcion(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findSuscripcionById(@PathVariable long id){
        try{
            return new ResponseEntity<>(service.getSuscripcionById(id), HttpStatus.OK);
        }catch (BusinessException e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/name/{nombrealumno}")
    public ResponseEntity<Object> findSuscripcionByNombrealumno(@PathVariable String nombrealumno) {
        try {
            return new ResponseEntity<>(service.getSuscripcionByNombrealumno(nombrealumno), HttpStatus.OK);
        } catch (BusinessException e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("")
    public ResponseEntity<Object> updateSuscripcion(@RequestBody Suscripcion suscripcion){
        try {
            service.updateSuscripcion(suscripcion);
            return new ResponseEntity<>(suscripcion,HttpStatus.OK);
        }catch (BusinessException e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSuscripcion(@PathVariable long id){
        try {
            service.deleteSuscripcion(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (BusinessException e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }

}
