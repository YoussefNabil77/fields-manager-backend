package com.fieldsmanager.fields_manager_backend.service;

import com.fieldsmanager.fields_manager_backend.entity.Enquiry;
import com.fieldsmanager.fields_manager_backend.entity.User;
import com.fieldsmanager.fields_manager_backend.repository.EnquiryRepository;
import com.fieldsmanager.fields_manager_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final UserRepository userRepository;

    public EnquiryService(EnquiryRepository enquiryRepository, UserRepository userRepository) {
        this.enquiryRepository = enquiryRepository;
        this.userRepository = userRepository;
    }

    public Enquiry createEnquiry(Integer userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Enquiry enquiry = new Enquiry();
        enquiry.setUser(user);
        enquiry.setContent(content);

        return enquiryRepository.save(enquiry);
    }

    public List<Enquiry> getAllEnquiries() {
        return enquiryRepository.findAll();
    }

    public Enquiry getEnquiryById(Integer id) {
        return enquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found"));
    }


    public void deleteEnquiry(Integer id) {
        if (!enquiryRepository.existsById(id)) {
            throw new RuntimeException("Enquiry not found with id " + id);
        }
        enquiryRepository.deleteById(id);
    }
}
