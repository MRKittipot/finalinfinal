package com.backend.server.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.server.models.UsersModel;
import com.backend.server.repository.UsersRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api", consumes = "application/json")
public class UsersController {
    
    @Autowired
    private UsersRepository usersRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @PostMapping(value="/register")
    public UsersModel getMethodName(@RequestBody UsersModel usersModel) {

        usersModel.setPassword(passwordEncoder().encode(usersModel.getPassword()));

        return usersRepository.save(usersModel);
    }


    @PostMapping(value="/login")
    public Boolean postMethodName(@RequestBody UsersModel entity) {
        UsersModel user = usersRepository.findByEmail(entity.getEmail());
        if(user != null){
            if(passwordEncoder().matches(entity.getPassword(), user.getPassword())){
                return true;
            }
        }
        return false;
    }
    
}
