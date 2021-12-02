package com.mycompany.doctorservice.service;

import com.mycompany.doctorservice.dto.*;
import com.mycompany.doctorservice.exception.DataAlreadyExistsException;
import com.mycompany.doctorservice.exception.DataNotFoundException;
import com.mycompany.doctorservice.model.Doctor;
import com.mycompany.doctorservice.model.WorkDay;
import com.mycompany.doctorservice.respository.DoctorRepository;
import com.mycompany.doctorservice.respository.VoivodeshipRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final VoivodeshipRepository voivodeshipRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<DoctorBasicDto> getAllWithBasicData() {
        var doctorBasicDtoListType = new TypeToken<List<DoctorBasicDto>>() {}.getType();
        return modelMapper.map(doctorRepository.findAll(), doctorBasicDtoListType);
    }

    @Transactional(readOnly = true)
    public DoctorDto get(UUID uuid) {
        var doctor = doctorRepository.findByDoctorUUID(uuid)
                .orElseThrow(() -> new DataNotFoundException(uuid.toString(), "User not found"));
        return modelMapper.map(doctor, DoctorDto.class);
    }

    @Transactional(readOnly = true)
    public DoctorWithContactDto getWithContact(UUID uuid) {
        var doctor = doctorRepository.findWithContactByDoctorUUID(uuid)
                .orElseThrow(() -> new DataNotFoundException(uuid.toString(), "User not found"));
        return modelMapper.map(doctor, DoctorWithContactDto.class);
    }

    @Transactional(readOnly = true)
    public DoctorWithWorkDaysDto getWithWorkDays(UUID uuid) {
        var doctor = doctorRepository.findWithWorkDaysByDoctorUUID(uuid)
                .orElseThrow(() -> new DataNotFoundException(uuid.toString(), "User not found"));
        return modelMapper.map(doctor, DoctorWithWorkDaysDto.class);
    }

    @Transactional(readOnly = true)
    public DoctorDto getWithContactAndWorkDays(UUID uuid) {
        var doctor = doctorRepository.findWithContactAndWorkDaysByDoctorUUID(uuid)
                .orElseThrow(() -> new DataNotFoundException(uuid.toString(), "User not found"));
        return modelMapper.map(doctor, DoctorDto.class);
    }

    @Transactional
    public DoctorDto update(CreateDoctorDto doctorDto, UUID uuid) {
        var doctor = doctorRepository.findWithContactAndWorkDaysByDoctorUUID(uuid)
                .orElseThrow(() -> new DataNotFoundException(uuid.toString(), "User not found"));
        List<WorkDayDto> workDays = doctorDto.getWorkDays();
        doctorDto.setWorkDays(null);
        modelMapper.map(doctorDto, doctor);
        doctor.getContact().setVoivodeship(voivodeshipRepository.getOne(doctor.getContact().getVoivodeship().getId()));
        doctor.getWorkDays().clear();
        doctorRepository.flush();
        doctor.getWorkDays().addAll(workDays.stream()
                .map(workDayDto -> new WorkDay(workDayDto.getWeekDay(), doctor, workDayDto.getStartTime(), workDayDto.getEndTime()))
                .collect(Collectors.toList()));
        return modelMapper.map(doctor, DoctorDto.class);
    }

    @Transactional
    public DoctorDto create(CreateDoctorDto doctorDto) {
        if (doctorRepository.existsByDoctorUUID(doctorDto.getDoctorUUID())) {
            throw new DataAlreadyExistsException(doctorDto.getDoctorUUID().toString(), "User already exists");
        }
        var doctor = modelMapper.map(doctorDto, Doctor.class);
        doctor.getContact().setDoctor(doctor);
        doctor.getContact().setVoivodeship(voivodeshipRepository.getOne(doctorDto.getContact().getVoivodeship().getId()));
        doctor.getWorkDays().addAll(doctorDto.getWorkDays().stream()
                .map(workDay -> new WorkDay(workDay.getWeekDay(), doctor, workDay.getStartTime(), workDay.getEndTime()))
                .collect(Collectors.toList()));

        doctorRepository.save(doctor);
        return modelMapper.map(doctorDto, DoctorDto.class);
    }

    @Transactional
    public void delete(UUID uuid) {
        doctorRepository.deleteByDoctorUUID(uuid);
    }
}
