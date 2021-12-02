package com.mycompany.doctorservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DoctorBasicDto {

    private UUID doctorUUID;

    private String firstName;

    private String middleName;

    private String lastName;
}