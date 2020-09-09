package com.vaescode.springboot.form.app.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.vaescode.springboot.form.app.models.domain.Usuario;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Value("${spring.view.resultado.titulo}")
	private String tituloResultado;
	
	@Value("${spring.view.form.titulo}")
	private String tituloForm;
	
	@GetMapping("/form")
	public String form(Model model) {
		
		Usuario usuario = new Usuario();
		usuario.setIdentificador("123.456.789-N");
		usuario.setNombre("John");
		usuario.setApellido("Doe");
		model.addAttribute("titulo", tituloForm);
		model.addAttribute("usuario", usuario);
		
		return "form";
	}
	
	@PostMapping("/form")
	public String procesar(@Valid @ModelAttribute("usuario") Usuario usuario,BindingResult result,Model model,SessionStatus status) {
		
		// No es necesario el setteo de los campos ya que nuestra clase POJO tiene el mismo nombre de campos que los campos del formulario
			//@RequestParam String username,
			//@RequestParam(name = "password") String password,
			//@RequestParam(value = "email") String email ) {
		
			//Usuario usuario = new Usuario();
			//usuario.setUsername(username);
			//usuario.setPassword(password);
			//usuario.setEmail(email);
		
		model.addAttribute("titulo", tituloResultado);
		
		if(result.hasErrors()) {
			
//			Map<String, String> errores = new HashMap<>();
//			
//			result.getFieldErrors().forEach(err -> {
//				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
//			});
//			
//			model.addAttribute("error", errores);
			
			
			
			return "form";
		}
		
		
		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "resultado";
	}
	
}
