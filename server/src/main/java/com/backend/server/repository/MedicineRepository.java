package com.backend.server.repository;


import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.backend.server.models.MedicineModel;

public interface MedicineRepository  extends CrudRepository<MedicineModel, String>{

    @Query(value = "SELECT * FROM news WHERE id = ?1", nativeQuery = true)
    MedicineModel findByNews_id(String id);

    @Query(value = "select * from medicine", nativeQuery = true)
    Iterable<MedicineModel> findAll();

    @Modifying
    @Transactional
    @Query(value = "insert into medicine(medicine_id, title, description, created_at, update_at, user_id) values(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void saveNewsModel(String id, String title, String description, LocalDateTime created_at, LocalDateTime update_at, String user_id);

    @Modifying
    @Transactional
    @Query(value = "delete from medicine where id = ?1", nativeQuery = true)
    void deleteByNews_id(String id);

    @Modifying
    @Transactional
    @Query(value = "update medicine set title = ?1, description = ?2, update_at = ?3 where medicine_id = ?4", nativeQuery = true)
    void updateNewsModel(String title, String description, LocalDateTime update_at, String id);
    
}