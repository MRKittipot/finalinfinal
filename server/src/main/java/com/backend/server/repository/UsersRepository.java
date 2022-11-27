package com.backend.server.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.backend.server.models.UsersModel;



public interface UsersRepository extends CrudRepository<UsersModel, String> {

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    UsersModel findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE user_id = ?1", nativeQuery = true)
    UsersModel findByUser_id(String id);
}
