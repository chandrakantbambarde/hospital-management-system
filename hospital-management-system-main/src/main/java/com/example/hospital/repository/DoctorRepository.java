package com.example.hospital.repository;

import com.example.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByEmail(String email);
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByLastNameContainingIgnoreCase(String lastName);
}