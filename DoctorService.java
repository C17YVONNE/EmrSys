package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Doctor;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean authenticate(String doctorid, String password) {
		Optional<Doctor> doctor = doctorRepository.findByDoctorid(doctorid);
		return doctor.isPresent() && passwordEncoder.matches(password, doctor.get().getPassword());
	}
}
