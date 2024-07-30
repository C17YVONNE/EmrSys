package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        String doctorId = request.getParameter("doctorId");
        String password = request.getParameter("password");

        if (userService.authenticate(doctorId, password) != null) {
            HttpSession session = request.getSession(true); 
            session.setAttribute("doctorId", doctorId);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "ログインエラーです。");
            return "login";
        }
    }
}
