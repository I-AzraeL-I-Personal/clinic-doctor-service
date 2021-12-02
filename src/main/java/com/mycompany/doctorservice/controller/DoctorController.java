package com.mycompany.doctorservice.controller;

import com.mycompany.doctorservice.dto.*;
import com.mycompany.doctorservice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<DoctorDto> get(@PathVariable UUID uuid) {
        return ResponseEntity.ok(doctorService.get(uuid));
    }

    @GetMapping(value = "/{uuid}/with-contact")
    public ResponseEntity<DoctorWithContactDto> getWithContact(@PathVariable UUID uuid) {
        return ResponseEntity.ok(doctorService.getWithContact(uuid));
    }

    @GetMapping(value = "/{uuid}/with-workdays")
    public ResponseEntity<DoctorWithWorkDaysDto> getWithWorkdays(@PathVariable UUID uuid) {
        return ResponseEntity.ok(doctorService.getWithWorkDays(uuid));
    }

    @GetMapping(value = "/{uuid}/with-contact-and-workdays")
    public ResponseEntity<DoctorDto> getWithContactAndWorkdays(@PathVariable UUID uuid) {
        return ResponseEntity.ok(doctorService.getWithContactAndWorkDays(uuid));
    }

    @GetMapping("")
    public ResponseEntity<List<DoctorBasicDto>> getAllWithBasicData() {
        return ResponseEntity.ok(doctorService.getAllWithBasicData());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<DoctorDto> update(@PathVariable UUID uuid, @Valid @RequestBody CreateDoctorDto doctorDto) {
        return ResponseEntity.ok(doctorService.update(doctorDto, uuid));
    }

    @PostMapping("")
    public ResponseEntity<DoctorDto> create(@Valid @RequestBody CreateDoctorDto doctorDto) {
        return ResponseEntity.ok(doctorService.create(doctorDto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        doctorService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
