package hn.edu.ujcv.p3.Proyecto3.service.multa;

import hn.edu.ujcv.p3.Proyecto3.entity.Multa;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultaService implements IMultaService {
    @Autowired
    private MultaRepository repository;

    public Multa saveMulta(Multa multa) throws BusinessException {
        try{
            if(String.valueOf(multa.getId()).length() > 10) {
                throw new Exception("Error! El Id se exede la cantidad de digitos permitidos(10)");
            }
            if(String.valueOf(multa.getId()).length() <=0){
                throw new BusinessException("El Id ingresado no es valido");
            }
            if(multa.getNombre().isEmpty()){
                throw new Exception("Error! El nombre no puede estar vacio");
            }
            if(multa.getDescripcion().isEmpty()){
                throw new Exception("Error! La descripcion no puede estar vacio");
            }
            if(String.valueOf(multa.getNumerocuenta()).length() > 12){
                throw new Exception("Error! El numero de cuenta no puede ser mayor a 12 digitos");
            }
            if(String.valueOf(multa.getNumerocuenta()).length() < 12){
                throw new Exception("Error! El numero de cuenta no puede ser menor a 12 digitos");
            }
            if(String.valueOf(multa.getMonto()).length() > 10) {
                throw new Exception("Error! El monto no puede ser mayor a 12 digitos");
            }
            if(multa.getMonto() <=0) {
                throw new Exception("Error! El monto no puede ser menor a 0");
            }
            if(multa.getFechacreacion().isEmpty()) {
                throw new Exception("Error! La fecha de creacion no puede estar vacia");
            }
            return repository.save(multa);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Multa> saveMultas(List<Multa> multa) throws BusinessException{
        try{
            return repository.saveAll(multa);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Multa> getMulta() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public Multa getMultaById(long id) throws BusinessException, NotFoundException {
        Optional<Multa> opt = null;
        try{
            if(id <=0){
                throw new BusinessException("El id ingresado no es valido");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la multa " + id);
        }
        return opt.get();
    }
    public Multa getMultaByName(String nombre)throws BusinessException, NotFoundException {
        Optional<Multa> opt = null;
        try{
            opt = repository.findByNombre(nombre);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la multa " + nombre);
        }
        return opt.get();
    }
    public void deleteMulta(long id)throws BusinessException, NotFoundException {
        Optional<Multa> opt = null;
        try{
            if(id <=0){
                throw new BusinessException("El id ingresado no es valido");
            }
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la multa " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Multa updateMulta(Multa multa)throws BusinessException,NotFoundException{
        Optional<Multa> opt;
        try{
            if(multa.getNombre().isEmpty()){
                throw new Exception("Error! El nombre no puede estar vacio");
            }
            if(multa.getDescripcion().isEmpty()){
                throw new Exception("Error! La descripcion no puede estar vacio");
            }
            if(String.valueOf(multa.getNumerocuenta()).length() > 12){
                throw new Exception("Error! El numero de cuenta no puede ser mayor a 12 digitos");
            }
            if(String.valueOf(multa.getNumerocuenta()).length() < 12){
                throw new Exception("Error! El numero de cuenta no puede ser menor a 12 digitos");
            }
            if(String.valueOf(multa.getMonto()).length() > 10) {
                throw new Exception("Error! El monto no puede ser mayor a 12 digitos");
            }
            if(multa.getMonto() <=0) {
                throw new Exception("Error! El monto no puede ser menor a 0");
            }
            if(multa.getFechacreacion().isEmpty()) {
                throw new Exception("Error! La fecha de creacion no puede estar vacia");
            }
            opt = repository.findById(multa.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el multa " + multa.getId());
        }
        else{
            try {
                Multa existingMulta=new Multa();
                existingMulta.setId(multa.getId());
                existingMulta.setNombre(multa.getNombre());
                existingMulta.setDescripcion(multa.getDescripcion());
                existingMulta.setNumerocuenta(multa.getNumerocuenta());
                existingMulta.setMonto(multa.getMonto());
                existingMulta.setFechacreacion(multa.getFechacreacion());
                return repository.save(existingMulta);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
