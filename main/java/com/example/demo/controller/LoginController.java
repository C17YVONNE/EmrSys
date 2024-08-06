package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Login;
import com.example.demo.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String doctorId, @RequestParam String password, HttpSession session,
			Model model) {
		Login login = loginService.authenticate(doctorId, password);
		if (login != null) {
			session.setAttribute("doctorId", doctorId);
			return "redirect:/home";
		} else {
			model.addAttribute("error", "ログインエラーです。");
			return "login";
		}
	}

	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		String doctorId = (String) session.getAttribute("doctorId");
		if (doctorId != null) {
			model.addAttribute("doctorId", doctorId);
			return "home";
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
