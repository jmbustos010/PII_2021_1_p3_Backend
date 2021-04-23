package hn.edu.ujcv.p3.Proyecto3.controller;


import hn.edu.ujcv.p3.Proyecto3.entity.Bibliotecario;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.bibliotecario.BibliotecarioService;
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
@RequestMapping("/api/v1/Bibliotecario")
public class BibliotecarioController {
    @Autowired
    private BibliotecarioService service;

    @PostMapping("/addBibliotecario")
    public ResponseEntity<Object> addBibliotecario(@RequestBody Bibliotecario bibliotecario) {
        try {
            service.saveBibliotecario(bibliotecario);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_BIBLIOTECARIO + bibliotecario.getId());
            return new ResponseEntity(bibliotecario, responseHeader, HttpStatus.CREATED);
        } catch (Exception e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //-----
    @PostMapping("/addBibliotecarios")
    public ResponseEntity<Object> addBibliotecarios(@RequestBody List<Bibliotecario> bibliotecario) {
        try {
            return new ResponseEntity(service.saveBibliotecario(bibliotecario), HttpStatus.CREATED);
        } catch (Exception e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //-----
    @GetMapping("")
    public ResponseEntity<List<Bibliotecario>> findAllBibliotecario() {
        try {
            return new ResponseEntity(service.getBibliotecario(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //-----
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findBibliotecarioById(@PathVariable long id) {
        try {
            return new ResponseEntity(service.getBibliotecarioById(id), HttpStatus.OK);
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
    public ResponseEntity<Object> findBibliotecarioByNombre(@PathVariable String nombre) {
        try {
            return new ResponseEntity(service.getBibliotecarioByName(nombre), HttpStatus.OK);
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
    public ResponseEntity<Object> updateBibliotecario(@RequestBody Bibliotecario bibliotecario) {
        try {
            service.updateBibliotecario(bibliotecario);
            return new ResponseEntity(bibliotecario, HttpStatus.OK);
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
    public ResponseEntity<Object> deleteBibliotecario(@PathVariable long id) {
        try {
            service.deleteBibliotecario(id);
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
