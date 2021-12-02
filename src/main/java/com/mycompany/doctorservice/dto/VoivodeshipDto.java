package com.mycompany.doctorservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VoivodeshipDto {

    @NotNull(message = "voivodeship id cannot be null")
    private Long id;

    private String voivodeshipName;
}
