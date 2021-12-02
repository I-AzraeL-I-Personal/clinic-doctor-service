package com.mycompany.doctorservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SpecialtyDto {

    @NotNull(message = "Specialty id cannot be null")
    private Long id;

    private String specialtyName;
}
