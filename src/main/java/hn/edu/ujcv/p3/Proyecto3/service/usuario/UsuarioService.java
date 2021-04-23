package hn.edu.ujcv.p3.Proyecto3.service.usuario;


import hn.edu.ujcv.p3.Proyecto3.entity.Usuario;
import hn.edu.ujcv.p3.Proyecto3.exceptions.BusinessException;
import hn.edu.ujcv.p3.Proyecto3.exceptions.NotFoundException;
import hn.edu.ujcv.p3.Proyecto3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario saveUsuario(Usuario usuario) throws BusinessException {
        try{
            if (usuario.getNombre().isEmpty()){
                throw new BusinessException("El primer nombre del usuario no puede estar vacio");
            }
            if (usuario.getApellido().isEmpty()){
                throw new BusinessException("El Apellido del usuario de libro no puede estar vacio");
            }
            if (usuario.getNombreusuario().isEmpty()){
                throw new BusinessException("El Nombre de usuario no puede estar vacio");
            }
            if (usuario.getContrasenia().isEmpty()){
                throw new BusinessException("La contraseña no puede esta vacio");
            }


            return repository.save(usuario);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public List<Usuario> saveUsuario(List<Usuario> usuarios) throws BusinessException{
        try{
            return repository.saveAll(usuarios);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public List<Usuario> getUsuario() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    //-----
    public Usuario getUsuarioById(long id) throws BusinessException, NotFoundException {
        Optional<Usuario> opt = null;
        try{

            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Usuario  " + id);
        }
        return opt.get();
    }
    //-----
    public Usuario getUsuarioByNombreusuario(String nombreusuario)throws BusinessException, NotFoundException {
        Optional<Usuario> opt = null;
        try{

            opt = repository.findBynombreusuario(nombreusuario);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Usuario  " + nombreusuario);
        }
        return opt.get();
    }
    //-----
    public void deleteUsuario(long id)throws BusinessException, NotFoundException {
        Optional<Usuario> opt = null;
        try{
            if (id <= 0){
                throw new BusinessException("El id que ingreso no es valido");
            }

            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Usuario  " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    //-----
    public Usuario updateUsuario(Usuario usuario)throws BusinessException,NotFoundException{
        Optional<Usuario> opt;
        try{
            opt = repository.findById(usuario.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Usuario " + usuario.getId());
        }
        else{
            if (usuario.getNombre().isEmpty()){
                throw new BusinessException("El primer nombre del usuario no puede estar vacio");
            }
            if (usuario.getApellido().isEmpty()){
                throw new BusinessException("El Apellido del usuario de libro no puede estar vacio");
            }
            if (usuario.getNombreusuario().isEmpty()){
                throw new BusinessException("El Nombre de usuario no puede estar vacio");
            }
            if (usuario.getContrasenia().isEmpty()){
                throw new BusinessException("La contraseña no puede esta vacio");
            }

            try {
                Usuario existingUsuario=new Usuario();
                existingUsuario.setId(usuario.getId());
                existingUsuario.setNombre(usuario.getNombre());
                existingUsuario.setApellido(usuario.getApellido());
                existingUsuario.setNombreusuario(usuario.getNombreusuario());
                existingUsuario.setContrasenia(usuario.getContrasenia());
                return repository.save(existingUsuario);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
}
