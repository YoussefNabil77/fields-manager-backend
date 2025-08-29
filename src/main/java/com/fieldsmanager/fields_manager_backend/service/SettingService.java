package com.fieldsmanager.fields_manager_backend.service;

import com.fieldsmanager.fields_manager_backend.entity.Setting;
import com.fieldsmanager.fields_manager_backend.repository.SettingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingService {

    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    // Create or update settings (usually only 1 row exists)
    public Setting saveSetting(Setting setting) {
        return settingRepository.save(setting);
    }

    // Get current settings
    public Optional<Setting> getSetting() {
        return settingRepository.findById(1); // assuming single settings row with ID = 1
    }

    // Update settings (if row exists, update it, otherwise create new)
    public Setting updateSetting(Setting updatedSetting) {
        return settingRepository.save(updatedSetting);
    }

    // Delete settings
    public void deleteSetting(Integer id) {
        settingRepository.deleteById(id);
    }
}
