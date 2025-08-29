package com.fieldsmanager.fields_manager_backend.controller;

import com.fieldsmanager.fields_manager_backend.dto.FieldSlotRequest;
import com.fieldsmanager.fields_manager_backend.dto.FieldSlotResponse;
import com.fieldsmanager.fields_manager_backend.entity.Field;
import com.fieldsmanager.fields_manager_backend.entity.FieldSlots;
import com.fieldsmanager.fields_manager_backend.service.FieldService;
import com.fieldsmanager.fields_manager_backend.service.FieldSlotsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/field-slots")
public class FieldSlotsController {

    private final FieldSlotsService fieldSlotsService;
    private final FieldService fieldService;

    public FieldSlotsController(FieldSlotsService fieldSlotsService, FieldService fieldService) {
        this.fieldSlotsService = fieldSlotsService;
        this.fieldService = fieldService;
    }

    @PostMapping
    public FieldSlotResponse addFieldSlot(@RequestBody FieldSlotRequest dto) {
        Field field = fieldService.getFieldById(dto.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));

        FieldSlots slot = new FieldSlots(
                field,
                dto.getWeekDay(),
                LocalTime.parse(dto.getFromTime()),
                LocalTime.parse(dto.getToTime()),
                dto.getPricePerHour()
        );

        FieldSlots saved = fieldSlotsService.addFieldSlot(slot);

        return new FieldSlotResponse(
                saved.getId(),
                saved.getField().getId(),
                saved.getWeekDay(),
                saved.getFromTime().toString(),
                saved.getToTime().toString(),
                saved.getPricePerHour()
        );
    }

    @GetMapping
    public List<FieldSlotResponse> getAllFieldSlots() {
        return fieldSlotsService.getAllFieldSlots().stream()
                .map(slot -> new FieldSlotResponse(
                        slot.getId(),
                        slot.getField().getId(),
                        slot.getWeekDay(),
                        slot.getFromTime().toString(),
                        slot.getToTime().toString(),
                        slot.getPricePerHour()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FieldSlotResponse getFieldSlotById(@PathVariable Integer id) {
        FieldSlots slot = fieldSlotsService.getFieldSlotById(id)
                .orElseThrow(() -> new RuntimeException("FieldSlot not found"));

        return new FieldSlotResponse(
                slot.getId(),
                slot.getField().getId(),
                slot.getWeekDay(),
                slot.getFromTime().toString(),
                slot.getToTime().toString(),
                slot.getPricePerHour()
        );
    }

    @PutMapping("/{id}")
    public FieldSlotResponse updateFieldSlot(@PathVariable Integer id, @RequestBody FieldSlotRequest dto) {
        Field field = fieldService.getFieldById(dto.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));

        FieldSlots updatedSlot = new FieldSlots(
                field,
                dto.getWeekDay(),
                LocalTime.parse(dto.getFromTime()),
                LocalTime.parse(dto.getToTime()),
                dto.getPricePerHour()
        );

        FieldSlots saved = fieldSlotsService.updateFieldSlot(id, updatedSlot);

        return new FieldSlotResponse(
                saved.getId(),
                saved.getField().getId(),
                saved.getWeekDay(),
                saved.getFromTime().toString(),
                saved.getToTime().toString(),
                saved.getPricePerHour()
        );
    }

    @DeleteMapping("/{id}")
    public String deleteFieldSlot(@PathVariable Integer id) {
        fieldSlotsService.deleteFieldSlot(id);
        return "FieldSlot deleted successfully!";
    }
}
