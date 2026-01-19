package com.example.hospital.controller;

import com.example.hospital.entity.Patient;
import com.example.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientApiController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Patient> searchPatients(@RequestParam String lastName) {
        return patientService.searchPatientsByLastName(lastName);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        try {
            // Check if email already exists
            Patient existingPatient = patientService.getPatientByEmail(patient.getEmail());
            if (existingPatient != null) {
                return ResponseEntity.badRequest().body(null);
            }

            Patient savedPatient = patientService.savePatient(patient);
            return ResponseEntity.ok(savedPatient);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            patient.setFirstName(patientDetails.getFirstName());
            patient.setLastName(patientDetails.getLastName());
            patient.setEmail(patientDetails.getEmail());
            patient.setPhoneNumber(patientDetails.getPhoneNumber());
            patient.setAddress(patientDetails.getAddress());
            patient.setDateOfBirth(patientDetails.getDateOfBirth());
            patient.setMedicalHistory(patientDetails.getMedicalHistory());

            Patient updatedPatient = patientService.savePatient(patient);
            return ResponseEntity.ok(updatedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            patientService.deletePatient(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}