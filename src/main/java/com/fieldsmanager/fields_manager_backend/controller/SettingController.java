package com.fieldsmanager.fields_manager_backend.controller;

import com.fieldsmanager.fields_manager_backend.entity.Setting;
import com.fieldsmanager.fields_manager_backend.service.SettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    // Get current settings
    @GetMapping
    public ResponseEntity<Setting> getSettings() {
        Optional<Setting> setting = settingService.getSetting();
        return setting.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new settings (if not exists)
    @PostMapping
    public ResponseEntity<Setting> createSetting(@RequestBody Setting setting) {
        Setting saved = settingService.saveSetting(setting);
        return ResponseEntity.ok(saved);
    }

    // Update existing settings
    @PutMapping("/{id}")
    public ResponseEntity<Setting> updateSetting(@PathVariable Integer id, @RequestBody Setting setting) {
        setting.setId(id); // ensure correct ID is set
        Setting updated = settingService.updateSetting(setting);
        return ResponseEntity.ok(updated);
    }

    // Delete settings
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetting(@PathVariable Integer id) {
        settingService.deleteSetting(id);
        return ResponseEntity.noContent().build();
    }
}
