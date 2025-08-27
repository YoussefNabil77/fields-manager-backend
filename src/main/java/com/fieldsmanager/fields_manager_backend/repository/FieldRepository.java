package com.fieldsmanager.fields_manager_backend.repository;

import com.fieldsmanager.fields_manager_backend.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Integer> {
}
