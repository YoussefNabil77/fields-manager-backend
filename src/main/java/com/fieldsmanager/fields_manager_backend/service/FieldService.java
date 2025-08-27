package com.fieldsmanager.fields_manager_backend.service;

import com.fieldsmanager.fields_manager_backend.entity.Field;
import com.fieldsmanager.fields_manager_backend.repository.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Field addField(Field field) {
        return fieldRepository.save(field);
    }

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Optional<Field> getFieldById(Integer id) {
        return fieldRepository.findById(id);
    }

    public Field updateField(Integer id, Field updatedField) {
        Field existingField = fieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field not found"));

        existingField.setName(updatedField.getName());
        existingField.setImages(updatedField.getImages());
        existingField.setPlayersCapacity(updatedField.getPlayersCapacity());

        return fieldRepository.save(existingField);
    }

    public void deleteField(Integer id) {
        fieldRepository.deleteById(id);
    }
}
