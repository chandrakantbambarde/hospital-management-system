package com.example.hospital.controller;

import com.example.hospital.entity.Patient;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.Appointment;
import com.example.hospital.service.PatientService;
import com.example.hospital.service.DoctorService;
import com.example.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public String home(Model model) {
        // Add stats for the home page
        model.addAttribute("patientCount", patientService.getAllPatients().size());
        model.addAttribute("doctorCount", doctorService.getAllDoctors().size());
        model.addAttribute("appointmentCount", appointmentService.getAllAppointments().size());
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("user", "Admin User");
        model.addAttribute("patientCount", patientService.getAllPatients().size());
        model.addAttribute("doctorCount", doctorService.getAllDoctors().size());
        model.addAttribute("appointmentCount", appointmentService.getAllAppointments().size());
        return "dashboard";
    }

    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("user", "Admin User");
        // Don't pass patients here - they will be loaded via AJAX
        return "patients";
    }

    @GetMapping("/doctors")
    public String doctors(Model model) {
        model.addAttribute("user", "Admin User");
        // Don't pass doctors here - they will be loaded via AJAX
        return "doctors";
    }

    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("user", "Admin User");
        // Don't pass appointments here - they will be loaded via AJAX
        return "appointments";
    }
}