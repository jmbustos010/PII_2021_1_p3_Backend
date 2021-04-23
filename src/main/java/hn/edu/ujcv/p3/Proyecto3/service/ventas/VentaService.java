package hn.edu.ujcv.p3.Proyecto3.service.ventas;

import hn.edu.ujcv.p3.Proyecto3.entity.Venta;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private VentaRepository repository;

    public Venta saveVenta(Venta venta) throws BusinessException {
        try{
            if (venta.getFechaventa().toString().isEmpty()) {
                throw new Exception("Error: Fecha incorrecta");
            }
            if (venta.getNombrelibro().isEmpty()) {
                throw new Exception("Error: EL nombre del libro no puede estar vacia");
            }
            if (venta.getId() <= 0) {
                throw new Exception("Error: El id de venta no puede ser menor o igua la 0");
            }
            if (venta.getNombrealumno().isEmpty()) {
                throw new Exception("Error: El nombre no puede estar vacia");
            }
            if (venta.getIdalumno() <= 0) {
                throw new Exception("Error: El id del alumno no puede ser menor o igua la 0");
            }
            if (venta.getPreciolibro() <= 0) {
                throw new Exception("Error: El precio del libro no puede ser menor o igua la 0");
            }
            return repository.save(venta);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Venta> saveVentas(List<Venta> ventas) throws BusinessException{
        try{
            return repository.saveAll(ventas);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    public List<Venta> getVenta() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public Venta getVentaById(long id) throws BusinessException, NotFoundException {
        Optional<Venta> opt = null;
        try{
            if (id <= 0) {
                throw new Exception("Error: El id del alumno no puede ser menor o igua la 0");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el id de la venta " + id);
        }
        return opt.get();
    }
    public Venta getVentaByIdAlumno(long idalumno)throws BusinessException, NotFoundException {
        Optional<Venta> opt = null;
        try{
            if (idalumno <= 0) {
                throw new Exception("Error: El id del alumno no puede ser menor o igual a 0");
            }
            opt = repository.findByIdalumno(idalumno);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("El id " + idalumno + " no tiene registrada ventas");
        }
        return opt.get();
    }
    public void deleteVenta(long id)throws BusinessException, NotFoundException {
        Optional<Venta> opt = null;
        try{
            if (id <= 0) {
                throw new Exception("Error: El id del alumno no puede ser menor o igual a 0");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el id de la venta " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Venta updateVenta(Venta venta)throws BusinessException,NotFoundException{
        Optional<Venta> opt;
        try{
            if (venta.getFechaventa().isEmpty()) {
                throw new Exception("Error: Fecha incorrecta");
            }
            if (venta.getNombrelibro().isEmpty()) {
                throw new Exception("Error: EL nombre del libro no puede estar vacia");
            }
            if (venta.getId() <= 0) {
                throw new Exception("Error: El id de venta no puede ser menor o igua la 0");
            }
            if (venta.getNombrealumno().isEmpty()) {
                throw new Exception("Error: El nombre no puede estar vacia");
            }
            if (venta.getIdalumno() <= 0) {
                throw new Exception("Error: El id del alumno no puede ser menor o igua la 0");
            }
            if (venta.getPreciolibro() <= 0) {
                throw new Exception("Error: El precio del libro no puede ser menor o igua la 0");
            }
            opt = repository.findById(venta.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el id de la  venta " + venta.getId());
        }
        else{
            try {
                Venta existingVenta = new Venta();
                existingVenta.setId(venta.getId());
                existingVenta.setNombrelibro(venta.getNombrelibro());
                existingVenta.setFechaventa(venta.getFechaventa());
                existingVenta.setNombrealumno(venta.getNombrealumno());
                existingVenta.setIdalumno(venta.getIdalumno());
                existingVenta.setPreciolibro(venta.getPreciolibro());
                return repository.save(existingVenta);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }




}
