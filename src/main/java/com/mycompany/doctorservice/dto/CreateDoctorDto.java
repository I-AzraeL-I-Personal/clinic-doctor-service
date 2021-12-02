package com.mycompany.doctorservice.dto;

import com.mycompany.doctorservice.model.Gender;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateDoctorDto {

    @NotNull(message = "doctor uuid cannot be null")
    private UUID doctorUUID;

    @NotBlank(message = "firstName cannot be blank")
    @Size(max = 50, message = "firstName cannot be longer than {max}")
    private String firstName;

    @Size(max = 50, message = "middleName cannot be longer than {max}")
    private String middleName;

    @NotBlank(message = "lastName cannot be blank")
    @Size(max = 50, message = "lastName cannot be longer than {max}")
    private String lastName;

    @PESEL(message = "Invalid pesel format")
    @NotNull(message = "pesel cannot be null")
    private String pesel;

    @NotNull(message = "gender cannot be null")
    private Gender gender;

    @NotNull(message = "employmentDate cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "employmentDate must be past value")
    private LocalDate employmentDate;

    @NotNull(message = "contact cannot be null")
    private ContactDto contact;

    @NotNull(message = "workDays cannot be null")
    private List<WorkDayDto> workDays;
}