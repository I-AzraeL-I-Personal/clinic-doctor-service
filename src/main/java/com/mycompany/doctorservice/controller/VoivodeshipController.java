package com.mycompany.doctorservice.controller;

import com.mycompany.doctorservice.dto.VoivodeshipDto;
import com.mycompany.doctorservice.service.VoivodeshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VoivodeshipController {

    private final VoivodeshipService voivodeshipService;

    @GetMapping("/voivodeships")
    public ResponseEntity<List<VoivodeshipDto>> getAllVoivodeships() {
        return ResponseEntity.ok(voivodeshipService.getAllVoivodeships());
    }
}
