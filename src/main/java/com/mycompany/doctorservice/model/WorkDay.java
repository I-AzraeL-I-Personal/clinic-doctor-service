package com.mycompany.doctorservice.model;

import com.mycompany.doctorservice.dto.WorkDayDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkDay {

    @EmbeddedId
    private WorkDayId id;

    @Column(nullable = false)
    @NotNull(message = "startTime cannot be null")
    private LocalTime startTime;

    @Column(nullable = false)
    @NotNull(message = "endTime cannot be null")
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("doctorId")
    private Doctor doctor;

    public WorkDay(DayOfWeek weekDay, Doctor doctor, LocalTime startTime, LocalTime endTime) {
        this.doctor = doctor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = new WorkDayId(doctor.getId(), weekDay);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkDay )) return false;
        return id != null && id.equals(((WorkDay) o).getId());
    }
}
