package com.example.plz.service;

import com.example.plz.dto.UserDto;
import com.example.plz.entity.User;
import com.example.plz.repository.UserReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserReposistory  userRepository;

    public void create(UserDto user){
        if (userRepository.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            throw new IllegalArgumentException("Password and Password confirm are not same");
        }
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        userRepository.save(newUser);
    }
    public void update(Long id, UserDto user){
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()){
            User updateUser = foundUser.get();
            updateUser.setName(user.getName());
            userRepository.save(updateUser);
        }
        else {
            throw new NoSuchElementException("User not found");
        }
    }
    public void delete(Long id){
        Optional<User> aUser = readOne(id);
        if (aUser.isPresent()){
            userRepository.delete(aUser.get());
        }
    }
    public Optional<User> readOne(Long id) {
        return userRepository.findById(id);
    }
    public List<User> readAll() {
        return userRepository.findAll();
    }
}
