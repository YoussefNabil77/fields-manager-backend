package com.fieldsmanager.fields_manager_backend.service;

import com.fieldsmanager.fields_manager_backend.entity.Field;
import com.fieldsmanager.fields_manager_backend.entity.FieldSlots;
import com.fieldsmanager.fields_manager_backend.repository.FieldSlotsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldSlotsService {

    private final FieldSlotsRepository fieldSlotsRepository;
    private final FieldService fieldService; // بدل FieldRepository

    public FieldSlotsService(FieldSlotsRepository fieldSlotsRepository, FieldService fieldService) {
        this.fieldSlotsRepository = fieldSlotsRepository;
        this.fieldService = fieldService;
    }

    public FieldSlots addFieldSlot(FieldSlots slot) {
        // optional: تحقق إن الـ Field موجود
        Field field = fieldService.getFieldById(slot.getField().getId())
                .orElseThrow(() -> new RuntimeException("Field not found"));
        slot.setField(field);
        return fieldSlotsRepository.save(slot);
    }

    public List<FieldSlots> getAllFieldSlots() {
        return fieldSlotsRepository.findAll();
    }

    public Optional<FieldSlots> getFieldSlotById(Integer id) {
        return fieldSlotsRepository.findById(id);
    }

    public FieldSlots updateFieldSlot(Integer id, FieldSlots updatedSlot) {
        FieldSlots existingSlot = fieldSlotsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FieldSlot not found"));

        Field field = fieldService.getFieldById(updatedSlot.getField().getId())
                .orElseThrow(() -> new RuntimeException("Field not found"));

        existingSlot.setField(field);
        existingSlot.setWeekDay(updatedSlot.getWeekDay());
        existingSlot.setFromTime(updatedSlot.getFromTime());
        existingSlot.setToTime(updatedSlot.getToTime());
        existingSlot.setPricePerHour(updatedSlot.getPricePerHour());

        return fieldSlotsRepository.save(existingSlot);
    }

    public void deleteFieldSlot(Integer id) {
        fieldSlotsRepository.deleteById(id);
    }
}
