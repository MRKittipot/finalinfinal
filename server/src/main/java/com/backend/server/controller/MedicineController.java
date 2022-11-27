package com.backend.server.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.server.models.MedicineModel;
import com.backend.server.models.UsersModel;
import com.backend.server.repository.MedicineRepository;
import com.backend.server.repository.UsersRepository;


@RestController
@RequestMapping(value = "/api", consumes = "application/json")
public class MedicineController {
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private UsersRepository usersRepository;


    @GetMapping(value="/medicine")
    public Object getAllNews() {
        return medicineRepository.findAll();
    }

    @GetMapping(value="/medicine/{id}")
    public MedicineModel getNewsById(@PathVariable("id") String id) {
        return medicineRepository.findByNews_id(id);
    }

    @PostMapping(value="/medicine")
    public void postNews(@RequestBody MedicineModel entity) {
        UsersModel usersModel = usersRepository.findByUser_id(entity.getUser().getUser_id());
    
        medicineRepository.saveNewsModel(entity.getMedicine_id(), entity.getTitle(), entity.getDescription(), LocalDateTime.now(), LocalDateTime.now(), usersModel.getUser_id());
    }

    @PutMapping(value="/medicine/{id}")
    public void putNews(@RequestBody MedicineModel entity, @PathVariable String id) {
        medicineRepository.updateNewsModel(entity.getTitle(), entity.getDescription(), LocalDateTime.now(), id);
    }


    @DeleteMapping(value="/medicine/{id}")
    public void deleteNews(@PathVariable String id) {
        medicineRepository.deleteById(id);
    }
}
