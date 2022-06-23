package com.bolsadeideas.springboot.milibreria.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required=false) String logout,
			Model model , Principal principal, RedirectAttributes attribute ) {
		if(error !=null) {
			model.addAttribute("error", "Usuario y/o Contraseña no validos!");
		}
		if(principal !=null) {
			attribute.addFlashAttribute("warning", "Sesion iniciada previamente");
			return "redirect:/";
		}
		
		if(logout !=null) {
			attribute.addFlashAttribute("success", "Sesion Finalizada");
			return "redirect:/";
		}
		return "login";

	}

}


