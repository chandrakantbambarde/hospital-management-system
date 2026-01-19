package com.example.hospital.controller;


import com.example.hospital.entity.Doctor;
import com.example.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorApiController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            return ResponseEntity.ok(doctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/specialization/{specialization}")
    public List<Doctor> getDoctorsBySpecialization(@PathVariable String specialization) {
        return doctorService.getDoctorsBySpecialization(specialization);
    }

    @GetMapping("/search")
    public List<Doctor> searchDoctors(@RequestParam String lastName) {
        return doctorService.searchDoctorsByLastName(lastName);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        try {
            // Check if email already exists
            Doctor existingDoctor = doctorService.getDoctorByEmail(doctor.getEmail());
            if (existingDoctor != null) {
                return ResponseEntity.badRequest().body(null);
            }

            Doctor savedDoctor = doctorService.saveDoctor(doctor);
            return ResponseEntity.ok(savedDoctor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            doctor.setFirstName(doctorDetails.getFirstName());
            doctor.setLastName(doctorDetails.getLastName());
            doctor.setEmail(doctorDetails.getEmail());
            doctor.setPhoneNumber(doctorDetails.getPhoneNumber());
            doctor.setSpecialization(doctorDetails.getSpecialization());
            doctor.setYearsOfExperience(doctorDetails.getYearsOfExperience());

            Doctor updatedDoctor = doctorService.saveDoctor(doctor);
            return ResponseEntity.ok(updatedDoctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            doctorService.deleteDoctor(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}