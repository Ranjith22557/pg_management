package com.example.pgmanagement.service;

import com.example.pgmanagement.model.UserEntity;
import com.example.pgmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveUser(UserEntity user){
        userRepository.save(user);
    }

    public Boolean existingUser(String email){
        Optional<UserEntity> existingUser = userRepository.findByEmail(email);
        return existingUser.isPresent();
    }
}
