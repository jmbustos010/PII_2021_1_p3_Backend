package hn.edu.ujcv.p3.Proyecto3.service.usuario;

import hn.edu.ujcv.p3.Proyecto3.entity.Usuario;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;

import java.util.List;

public interface IUsuarioService {
    Usuario saveUsuario(Usuario usuario)throws BusinessException;
    List<Usuario> saveUsuario(List<Usuario> usuarios) throws BusinessException;
    List<Usuario> getUsuario() throws BusinessException;
    Usuario getUsuarioById(long id) throws BusinessException, NotFoundException;
    Usuario getUsuarioByNombreusuario(String nombreusuario)throws BusinessException, NotFoundException;
    void deleteUsuario(long Id)throws BusinessException, NotFoundException;
    Usuario updateUsuario (Usuario usuario)throws BusinessException, NotFoundException;
}
