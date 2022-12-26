package com.example.crudbatis.user.infraestructure.controller;

import com.example.crudbatis.exception.UserIdAlreadyExistException;
import com.example.crudbatis.exception.UserIdNotFoundException;
import com.example.crudbatis.user.domain.User;
import com.example.crudbatis.user.infraestructure.repository.mapper.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //mostrar todos los usuarios
    @GetMapping("/users")
    public List<User> findAll(){
        return userRepository.findAll();

    }

    //crear usuarios
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        if(userRepository.findById(user.getId())==null){
                int id = userRepository.insert(user);
                return userRepository.findById(id);
        }else{
            throw new UserIdAlreadyExistException();
        }
    }
    //mostrar usuario por id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userRepository.findById(id);
        if(user==null){
            throw new UserIdNotFoundException();
        }
        return ResponseEntity.ok(user);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody User userDetails) {
        if(userRepository.update(new User(id, userDetails.getFirstName(),
                userDetails.getLastName(), userDetails.getEmailId()))==0)
        {
            throw new UserIdNotFoundException();
        }

        return ResponseEntity.ok(userRepository.findById(id));
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }




}
