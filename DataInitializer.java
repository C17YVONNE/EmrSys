package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Doctor;
import com.example.demo.repository.DoctorRepository;

@Component
public class DataInitializer implements CommandLineRunner {
	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// Encrypt and save a sample doctor for testing
		Doctor doctor = new Doctor();
		doctor.setDoctorid("uuu123");
		doctor.setPassword(passwordEncoder.encode("uuu123")); // Encrypting password
		doctorRepository.save(doctor);
	}
}
