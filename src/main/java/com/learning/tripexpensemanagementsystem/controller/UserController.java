package com.learning.tripexpensemanagementsystem.controller;

import com.learning.tripexpensemanagementsystem.dto.JwtAuthResponseDto;
import com.learning.tripexpensemanagementsystem.dto.LoginDto;
import com.learning.tripexpensemanagementsystem.dto.RegisterRequestDto;
import com.learning.tripexpensemanagementsystem.dto.UserDto;
import com.learning.tripexpensemanagementsystem.service.TripService;
import com.learning.tripexpensemanagementsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final TripService tripService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpSession session, Model model) {
        JwtAuthResponseDto response = userService.login(loginDto);
        if (response != null) {
            session.setAttribute("userId", response.getUserId());
            session.setAttribute("token", response.getTokenType() + " " + response.getAccessToken());
            return "redirect:/api/user/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerRequestDto", new RegisterRequestDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequestDto registerRequestDto, Model model) {
        UserDto response = userService.register(registerRequestDto);
        if (response != null) {
            return "redirect:/api/user/login";
        } else {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "/register";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        model.addAttribute("trips", tripService.getAll());
        var userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            model.addAttribute("error", "User not authenticated");
            return "login";
        }

        var token = (String) session.getAttribute("token");
        model.addAttribute("token", token);
        model.addAttribute("userId", userId);
        model.addAttribute("user", userService.getById(userId));
        return "home";
    }
}
