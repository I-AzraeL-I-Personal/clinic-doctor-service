package com.mycompany.doctorservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
public class WorkDayDto {

    @NotNull(message = "weekDay cannot be null")
    private DayOfWeek weekDay;

    @NotNull(message = "startTime cannot be null")
    private LocalTime startTime;

    @NotNull(message = "endTime cannot be null")
    private LocalTime endTime;
}
