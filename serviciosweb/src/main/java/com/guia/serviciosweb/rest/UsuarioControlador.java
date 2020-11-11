package com.guia.serviciosweb.rest;

import com.guia.serviciosweb.model.UsuarioModelo;
import com.guia.serviciosweb.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/usuarios")
    public List<UsuarioModelo> obtenerUsuarios(){
        return usuarioServicio.obtenerTodos();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioModelo> obtenerUsuario(@PathVariable("id") Integer id){
        UsuarioModelo usuario = usuarioServicio.obtenerUno(id);
        return usuario == null ?
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/usuarios")
    public UsuarioModelo guardarUsuario(@RequestBody UsuarioModelo usuario){
        usuarioServicio.guardar(usuario);
        return usuario;
    }

    @PutMapping("/usuarios")
    public ResponseEntity<UsuarioModelo> actualizarUsuario(@RequestBody UsuarioModelo usuario){
        UsuarioModelo aux = usuarioServicio.actualizar(usuario);
        return aux == null ?
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(aux, HttpStatus.OK);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Boolean> eliminarUsuario(@PathVariable("id") Integer id){
        Boolean flag = usuarioServicio.eliminar(id);
        return !flag ?
                new ResponseEntity<>(false, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(true, HttpStatus.OK);
    }
}
