package com.example.hospital.service;

import com.example.hospital.entity.Patient;
import com.example.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElse(null);
    }

    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public List<Patient> searchPatientsByLastName(String lastName) {
        return patientRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}