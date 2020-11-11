package com.guia.serviciosweb.rest;

import com.guia.serviciosweb.model.UserEntity;
import com.guia.serviciosweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/usuarios")
    public List<UserEntity> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Integer id){
        UserEntity user = userService.getByID(id);
        return user == null ?
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/usuarios")
    public UserEntity addUser(@RequestBody UserEntity user){
        userService.add(user);
        return user;
    }

    @PutMapping("/usuarios")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user){
        UserEntity aux = userService.update(user);
        return aux == null ?
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(aux, HttpStatus.OK);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Integer id){
        Boolean flag = userService.delete(id);
        return !flag ?
                new ResponseEntity<>(false, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(true, HttpStatus.OK);
    }
}
