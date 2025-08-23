package com.fieldsmanager.fields_manager_backend.repository;
//
import com.fieldsmanager.fields_manager_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
