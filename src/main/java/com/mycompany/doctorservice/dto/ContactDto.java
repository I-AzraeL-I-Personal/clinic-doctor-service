package com.mycompany.doctorservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ContactDto {

    @NotBlank(message = "phoneNumber cannot be blank")
    @Pattern(regexp="[\\d]{9}", message = "Invalid phoneNumber format")
    private String phoneNumber;

    @NotNull(message = "voivodeship cannot be null")
    private VoivodeshipDto voivodeship;

    @NotBlank(message = "city cannot be blank")
    @Size(max = 100, message = "city cannot be longer than {max}")
    private String city;

    @NotBlank(message = "street cannot be blank")
    @Size(max = 100, message = "street cannot be longer than {max}")
    private String street;

    @NotBlank(message = "houseNum cannot be blank")
    @Size(max = 10, message = "houseNum cannot be longer than {max}")
    private String houseNum;

    @Size(max = 10, message = "flatNum cannot be longer than {max}")
    private String flatNum;
}
