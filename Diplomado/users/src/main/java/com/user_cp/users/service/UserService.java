package com.user_cp.users.service;

import com.user_cp.users.model.UserEntity;
import com.user_cp.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity add(UserEntity user){
        try{
            return userRepository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }

    public UserEntity getByID(Integer id){
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get();
        }
        return null;
    }

    public UserEntity update(UserEntity user){
        Integer id = user.getId();
        if(userRepository.existsById(id)){
            return userRepository.save(user);
        }
        return null;
    }

    public Boolean delete(Integer id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
