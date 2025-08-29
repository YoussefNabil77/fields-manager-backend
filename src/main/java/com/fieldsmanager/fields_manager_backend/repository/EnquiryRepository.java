package com.fieldsmanager.fields_manager_backend.repository;


import com.fieldsmanager.fields_manager_backend.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {
}