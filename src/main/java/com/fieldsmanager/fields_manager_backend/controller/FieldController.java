package com.fieldsmanager.fields_manager_backend.controller;

import com.fieldsmanager.fields_manager_backend.entity.Field;
import com.fieldsmanager.fields_manager_backend.service.FieldService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping
    public Field addField(@RequestBody Field field) {
        return fieldService.addField(field);
    }

    @GetMapping
    public List<Field> getAllFields() {
        return fieldService.getAllFields();
    }

    @GetMapping("/{id}")
    public Field getFieldById(@PathVariable Integer id) {
        return fieldService.getFieldById(id).orElseThrow(() -> new RuntimeException("Field not found"));
    }

    @PutMapping("/{id}")
    public Field updateField(@PathVariable Integer id, @RequestBody Field field) {
        return fieldService.updateField(id, field);
    }

    @DeleteMapping("/{id}")
    public String deleteField(@PathVariable Integer id) {
        fieldService.deleteField(id);
        return "Field deleted successfully!";
    }
}
