package hn.edu.ujcv.p3.Proyecto3.controller;

import hn.edu.ujcv.p3.Proyecto3.entity.Reposicion;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.reposicion.ReposicionService;
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
@RequestMapping("/api/v1/Reposicion")
public class ReposicionController {
    @Autowired
    private ReposicionService service;
    @PostMapping("/addReposicion")
    public ResponseEntity<Object> addReposicion(@RequestBody Reposicion reposicion){
        try{
            service.saveReposicion(reposicion);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_REPOSICION + reposicion.getId());
            return new ResponseEntity(reposicion,responseHeader, HttpStatus.CREATED);
        }
        catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no valida", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addReposiciones")
    public ResponseEntity<Object> addReposiciones(@RequestBody List<Reposicion> Reposicion){
        try{
            return new ResponseEntity(service.saveReposiciones(Reposicion), HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no valida", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Reposicion>> findAllReposicion(){
        try{
            return new ResponseEntity(service.getReposicion(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findReposicionById(@PathVariable long id){
        try{
            return new ResponseEntity(service.getReposicionById(id), HttpStatus.OK);
        }catch (BusinessException e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no valida", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/cuenta/{numerocuentaalumno}")
    public ResponseEntity<Object> findReposicionByNumerocuentaalumno(@PathVariable long numerocuentaalumno){
        try{
            return new ResponseEntity(service.getReposicionByNumerocuentaalumno(numerocuentaalumno), HttpStatus.OK);
        }catch (BusinessException e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no valida", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("")
    public ResponseEntity<Object> updateReposicion(@RequestBody Reposicion reposicion){
        try {
            service.updateReposicion(reposicion);
            return new ResponseEntity(reposicion,HttpStatus.OK);
        }catch (BusinessException e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no valida", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteReposicion(@PathVariable long id){
        try {
            service.deleteReposicion(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no valida", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                    "Informacion ingresada no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }
    }
}
