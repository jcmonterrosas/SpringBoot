package com.guia.serviciosweb.soap;

import java.util.ArrayList;
import java.util.List;

import com.guia.serviciosweb.model.UserEntity;
import com.guia.serviciosweb.service.UserService;
import localhost.usuarios.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class UsersEndpoint {

    public static final String NAMESPACE_URI = "http://localhost/usuarios";

    private UserService userService;

    public UsersEndpoint(){

    }

    @Autowired
    public UsersEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot( namespace = NAMESPACE_URI, localPart = "getUserByIdRequest" )
    @ResponsePayload
    public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest request){
        GetUserByIdResponse response = new GetUserByIdResponse();
        UserEntity userEntity = userService.getByID(request.getUserId());
        User user = new User();
        user.setUserId(userEntity.getId());
        BeanUtils.copyProperties(userEntity, user);
        response.setUser(user);
        return response;
    }

    @PayloadRoot( namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request){
        GetAllUsersResponse response = new GetAllUsersResponse();
        List<User> userList = new ArrayList<User>();
        List<UserEntity> userEntityList = userService.getAll();
        for (UserEntity userEntity : userEntityList){
            User user = new User();
            user.setUserId(userEntity.getId());
            BeanUtils.copyProperties(userEntity, user);
            userList.add(user);
        }
        response.getUser().addAll(userList);
        return response;
    }

    @PayloadRoot( namespace = NAMESPACE_URI, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse addUser(@RequestPayload AddUserRequest request) {
        AddUserResponse response = new AddUserResponse();
        User newUser = new User();
        ServiceStatus serviceStatus = new ServiceStatus();

        UserEntity newUserEntity = new UserEntity(request.getFirstname(), request.getLastname(), request.getAddress(), request.getGenre(), request.getAge());
        UserEntity savedUserEntity = userService.add(newUserEntity);

        if (savedUserEntity == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Fallo guardando el usuario");
        } else {
            newUser.setUserId(savedUserEntity.getId());
            BeanUtils.copyProperties(savedUserEntity, newUser);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Usuario añadido exitosamente");
        }

        response.setUser(newUser);
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
        UpdateUserResponse response = new UpdateUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        UserEntity userEntity = new UserEntity(request.getUserId(),request.getFirstname(), request.getLastname(), request.getAddress(), request.getGenre(), request.getAge());
        UserEntity updatedUser = userService.update(userEntity);

        if(updatedUser == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Usuario = " + request.getFirstname() + " " + request.getLastname() + " no existe en la base de datos");
        }else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Usuario actualizado exitosamente");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
        DeleteUserResponse response = new DeleteUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean flag = userService.delete(request.getUserId());

        if (!flag) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Fallo eliminando el usuario con id = " + request.getUserId());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Usuario eliminado exitosamente");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }
}
