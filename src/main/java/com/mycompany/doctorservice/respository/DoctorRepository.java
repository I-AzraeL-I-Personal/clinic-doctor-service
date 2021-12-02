package com.mycompany.doctorservice.respository;

import com.mycompany.doctorservice.model.Doctor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @EntityGraph("doctor.contact")
    Optional<Doctor> findWithContactByDoctorUUID(UUID doctorUUID);

    @EntityGraph("doctor.workDays")
    Optional<Doctor> findWithWorkDaysByDoctorUUID(UUID doctorUUID);

    @EntityGraph("doctor.contact+workDays")
    Optional<Doctor> findWithContactAndWorkDaysByDoctorUUID(UUID doctorUUID);

    Optional<Doctor> findByDoctorUUID(UUID doctorUUID);
    boolean existsByDoctorUUID(UUID doctorUUID);
    void deleteByDoctorUUID(UUID doctorUUID);
}
