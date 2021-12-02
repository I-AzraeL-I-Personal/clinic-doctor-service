package com.mycompany.doctorservice.dto;

import com.mycompany.doctorservice.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class DoctorWithContactDto {

    private UUID doctorUUID;

    private String firstName;

    private String middleName;

    private String lastName;

    private String pesel;

    private Gender gender;

    private LocalDate employmentDate;

    private ContactDto contact;
}
