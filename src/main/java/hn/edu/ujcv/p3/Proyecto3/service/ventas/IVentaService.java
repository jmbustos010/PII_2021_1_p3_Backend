package hn.edu.ujcv.p3.Proyecto3.service.ventas;

import hn.edu.ujcv.p3.Proyecto3.entity.Venta;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface IVentaService {

    Venta saveVenta(Venta venta) throws BusinessException;
    List<Venta> saveVentas(List<Venta> ventas) throws BusinessException;
    List<Venta> getVenta() throws BusinessException;
    Venta getVentaById(long id) throws BusinessException, NotFoundException;
    Venta getVentaByIdAlumno(long idalumno)throws BusinessException, NotFoundException;
    void deleteVenta(long Id)throws BusinessException, NotFoundException;
    Venta updateVenta (Venta venta)throws BusinessException, NotFoundException;


}
