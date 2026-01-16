package com.app.Quiz_Application.security.controller;

import com.app.Quiz_Application.config.SecurityConfig;
import com.app.Quiz_Application.question.model.Question;
import com.app.Quiz_Application.security.service.CustomSecurityService;
import com.app.Quiz_Application.question.service.QuizService;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SecurityController {

    private CustomSecurityService securityService;
    private AuthenticationManager authenticationManager;
    private QuizService service;

    public SecurityController(CustomSecurityService securityService, AuthenticationManager authenticationManager, QuizService service){
        this.securityService = securityService;
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    @GetMapping("/home")
    public String home(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/login?error";
        }

        String role = authentication
                .getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).findFirst().orElse("QuizTaker");

        if(role.equals("ROLE_ADMIN")) return "redirect:/admin";
        else return "redirect:/quizTaker";

    }

    @GetMapping("/login")
    public  String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/quizTaker")
    public String quizTaker(Model model){
        List<Question> questions = service.getAllQuestions();
        model.addAttribute("Questions",questions);
        return "quizTaker";
    }

    @PostMapping("/register")
    public String register(@RequestParam  String username,
                         @RequestParam  String password,
                         @RequestParam  String email,
                         @RequestParam  String role
    ){
        try{
            securityService.registerUser(username,password,email,role);
        } catch(Exception userAlreadyExists) {
            return "redirect/login";
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/login?success";
    }
}