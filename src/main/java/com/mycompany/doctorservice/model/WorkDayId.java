package com.mycompany.doctorservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkDayId implements Serializable {

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek weekDay;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkDayId that = (WorkDayId) o;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(weekDay, that.weekDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, weekDay);
    }
}
