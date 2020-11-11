package com.guia.serviciosweb.service;

import com.guia.serviciosweb.model.UsuarioModelo;
import com.guia.serviciosweb.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /*public void guardar(UsuarioModelo usuario){
        usuarioRepositorio.save(usuario);
    }*/
    public UsuarioModelo guardar(UsuarioModelo usuario){
        try{
            return usuarioRepositorio.save(usuario);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<UsuarioModelo> obtenerTodos(){
        return usuarioRepositorio.findAll();
    }

    public UsuarioModelo obtenerUno(Integer id){
        if(usuarioRepositorio.existsById(id)){
            return usuarioRepositorio.findById(id).get();
        }
        return null;
    }

    public UsuarioModelo actualizar(UsuarioModelo usuario){
        Integer id = usuario.getId();
        if(usuarioRepositorio.existsById(id)){
            return usuarioRepositorio.save(usuario);
        }
        return null;
    }

    public Boolean eliminar(Integer id){
        if(usuarioRepositorio.existsById(id)) {
            usuarioRepositorio.deleteById(id);
            return true;
        }
        return false;
    }
}
