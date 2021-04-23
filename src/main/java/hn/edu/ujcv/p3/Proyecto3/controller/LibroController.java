package hn.edu.ujcv.p3.Proyecto3.controller;


import hn.edu.ujcv.p3.Proyecto3.entity.Libro;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.libro.LibroService;
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
@RequestMapping("/api/v1/Libro")
public class LibroController {
    @Autowired
    private LibroService service;

    @PostMapping("/addLibro")
    public ResponseEntity<Object> addLibro(@RequestBody Libro libro) {
        try {
            service.saveLibro(libro);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_LIBRO + libro.getId());
            return new ResponseEntity(libro, responseHeader, HttpStatus.CREATED);
        } catch (Exception e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //-----
    @PostMapping("/addLibros")
    public ResponseEntity<Object> addLibro(@RequestBody List<Libro> libros) {
        try {
            return new ResponseEntity(service.saveLibros(libros), HttpStatus.CREATED);
        } catch (Exception e) {
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion no valida", e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //-----
    @GetMapping("")
    public ResponseEntity<List<Libro>> findAllBLibro() {
        try {
            return new ResponseEntity(service.getLibro(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //-----
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findLibroById(@PathVariable long id) {
        try {
            return new ResponseEntity(service.getLibroById(id), HttpStatus.OK);
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
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Object> findLibroByTitulo(@PathVariable String titulo) {
        try {
            return new ResponseEntity(service.getLibroByTitulo(titulo), HttpStatus.OK);
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
    public ResponseEntity<Object> updateLibro(@RequestBody Libro libro) {
        try {
            service.updateLibro(libro);
            return new ResponseEntity(libro, HttpStatus.OK);
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
    public ResponseEntity<Object> deleteLibro(@PathVariable long id) {
        try {
            service.deleteLibro(id);
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
