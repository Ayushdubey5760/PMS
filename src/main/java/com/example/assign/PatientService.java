package com.example.assign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient getPatientById(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient) {
        Patient existingPatient = getPatientById(id);
        existingPatient.setFirstName(patient.getFirstName());
        existingPatient.setLastName(patient.getLastName());
        // Update other fields similarly...
        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {
        Patient existingPatient = getPatientById(id);
        patientRepository.delete(existingPatient);
    }
}