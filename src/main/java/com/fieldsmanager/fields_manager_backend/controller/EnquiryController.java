package com.fieldsmanager.fields_manager_backend.controller;

import com.fieldsmanager.fields_manager_backend.entity.Enquiry;
import com.fieldsmanager.fields_manager_backend.service.EnquiryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    private final EnquiryService enquiryService;

    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    // Create enquiry (Player)
    @PostMapping
    public Enquiry createEnquiry(@RequestParam Integer userId, @RequestParam String content) {
        return enquiryService.createEnquiry(userId, content);
    }

    // Get all enquiries (Admin)
    @GetMapping
    public List<Enquiry> getAllEnquiries() {
        return enquiryService.getAllEnquiries();
    }

    // Get enquiry by id
    @GetMapping("/{id}")
    public Enquiry getEnquiry(@PathVariable Integer id) {
        return enquiryService.getEnquiryById(id);
    }

    // DELETE enquiry by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnquiry(@PathVariable Integer id) {
        enquiryService.deleteEnquiry(id);
        return ResponseEntity.noContent().build();
    }
}
