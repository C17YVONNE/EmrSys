package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.DoctorService;

@Controller
public class LoginController {
	@Autowired
	private DoctorService doctorService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String doctorid, @RequestParam String password, Model model) {
		if (doctorService.authenticate(doctorid, password)) {
			return "home";
		} else {
			model.addAttribute("error", "ログインエラーです。");
			return "login";
		}
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("error", "エラーです。");
		return "login";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}
}
