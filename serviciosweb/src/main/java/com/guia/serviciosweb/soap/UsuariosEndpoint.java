package com.guia.serviciosweb.soap;

import java.util.ArrayList;
import java.util.List;

import com.guia.serviciosweb.model.UsuarioModelo;
import com.guia.serviciosweb.service.UsuarioServicio;
import localhost.usuarios.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class UsuariosEndpoint {

    public static final String NAMESPACE_URI = "http://localhost/usuarios";

    private UsuarioServicio servicio;

    public UsuariosEndpoint(){

    }

    @Autowired
    public UsuariosEndpoint(UsuarioServicio servicio) {
        this.servicio = servicio;
    }

    @PayloadRoot( namespace = NAMESPACE_URI, localPart = "getUserByIdRequest" )
    @ResponsePayload
    public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest solicitud){
        GetUserByIdResponse respuesta = new GetUserByIdResponse();
        UsuarioModelo usuarioModelo = servicio.obtenerUno(solicitud.getUsuarioId());
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioModelo.getId());
        BeanUtils.copyProperties(usuarioModelo, usuario);
        respuesta.setUsuario(usuario);
        return respuesta;
    }

    @PayloadRoot( namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest solicitud){
        GetAllUsersResponse respuesta = new GetAllUsersResponse();
        List<Usuario> usuariosLista = new ArrayList<Usuario>();
        List<UsuarioModelo> usuarioModeloLista = servicio.obtenerTodos();
        for (UsuarioModelo usuarioModelo : usuarioModeloLista){
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(usuarioModelo.getId());
            BeanUtils.copyProperties(usuarioModelo, usuario);
            usuariosLista.add(usuario);
        }
        respuesta.getUsuario().addAll(usuariosLista);
        return respuesta;
    }

    @PayloadRoot( namespace = NAMESPACE_URI, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse addUser(@RequestPayload AddUserRequest solicitud) {
        AddUserResponse respuesta = new AddUserResponse();
        Usuario nuevoUsuario = new Usuario();
        ServiceStatus serviceStatus = new ServiceStatus();

        UsuarioModelo nuevoUsuarioModelo = new UsuarioModelo(solicitud.getNombres(), solicitud.getApellidos(), solicitud.getDireccion(), solicitud.getGenero(), solicitud.getEdad());
        UsuarioModelo usuarioModeloGuardado = servicio.guardar(nuevoUsuarioModelo);

        if (usuarioModeloGuardado == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding Entity");
        } else {
            BeanUtils.copyProperties(usuarioModeloGuardado, nuevoUsuario);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
        }

        respuesta.setUsuario(nuevoUsuario);
        respuesta.setServiceStatus(serviceStatus);
        return respuesta;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
        UpdateUserResponse response = new UpdateUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        UsuarioModelo usuarioModelo = new UsuarioModelo(request.getUsuarioId(),request.getNombres(), request.getApellidos(), request.getDireccion(), request.getGenero(), request.getEdad());
        UsuarioModelo updatedUser = servicio.actualizar(usuarioModelo);

        if(updatedUser == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Usuario = " + request.getNombres() + " " + request.getApellidos() + " no existe en la base de datos");
        }else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content updated Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
        DeleteUserResponse response = new DeleteUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean flag = servicio.eliminar(request.getUsuarioId());

        if (!flag) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deletint Entity id = " + request.getUsuarioId());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }
}
