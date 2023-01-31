package br.com.sistema.acme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/templates/index/")
	public String index(){
		return "index";
	}
}
