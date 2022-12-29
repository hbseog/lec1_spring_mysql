package com.example.plz.controller;

import com.example.plz.dto.UserDto;
import com.example.plz.entity.User;
import com.example.plz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    private void create(@RequestBody UserDto requestCreated){
        userService.create(requestCreated);
    }
    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody UserDto requestUpdated){
        userService.update(id,requestUpdated);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
    @GetMapping("{id}")
    public User readOne(@PathVariable Long id){
        return userService.readOne(id).orElse(null);
    }
    @GetMapping
    public List<User> readAll(){
        return userService.readAll();
    }
}