package com.fieldsmanager.fields_manager_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class FieldsManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FieldsManagerBackendApplication.class, args);
	}


}
