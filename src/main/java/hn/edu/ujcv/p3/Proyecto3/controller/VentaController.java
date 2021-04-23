package hn.edu.ujcv.p3.Proyecto3.controller;

import hn.edu.ujcv.p3.Proyecto3.entity.Tutoria;
import hn.edu.ujcv.p3.Proyecto3.entity.Venta;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.service.ventas.VentaService;
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
@RequestMapping("/api/v1/Venta")

public class VentaController {
    @Autowired
    private VentaService service;

    @PostMapping("/addVenta")
    public ResponseEntity<Object> addVenta(@RequestBody Venta venta){
        try{
            service.saveVenta(venta);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_VENTA + venta.getId());
            return new ResponseEntity<>(venta,responseHeader, HttpStatus.CREATED);
        }
        catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addVentas")
    public ResponseEntity<Any> addVentas(@RequestBody List<Venta> ventas){
        try{
            return new ResponseEntity(service.saveVentas(ventas), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Venta>> findAllVentas(){
        try{
            return new ResponseEntity(service.getVenta(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/alumnoid/{idalumno}")
    public ResponseEntity<Object> findVentaByIdalumno(@PathVariable long idalumno){
        try{
            return new ResponseEntity<>(service.getVentaByIdAlumno(idalumno), HttpStatus.OK);
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
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findVentaById(@PathVariable long id){
        try{
            return new ResponseEntity<>(service.getVentaById(id), HttpStatus.OK);
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
    @PutMapping("")
    public ResponseEntity<Object> updateVenta(@RequestBody Venta venta){
        try {
            service.updateVenta(venta);
            return new ResponseEntity<>(venta,HttpStatus.OK);
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteVenta(@PathVariable long id){
        try {
            service.deleteVenta(id);
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
