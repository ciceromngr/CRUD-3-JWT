package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsuarioLoginDto;
import com.example.demo.models.Usuario;
import com.example.demo.service.UserAuthenticationService;
import com.example.demo.service.UserResgistrationService;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UsuarioController {

	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UserAuthenticationService userAuthenticationService;
	
	@Autowired
	private UserResgistrationService userRegistrationService;
	
	@GetMapping
	public List<Usuario> pegarTodosUsuarios() throws Exception{
		return usuarioService.pegarTodosUsuario();
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) throws Exception {
		userRegistrationService.registrarComToken(usuario);		
		return ResponseEntity.ok().body(usuario.getToken().toString());
	}
	
	@PostMapping("/login")
	public Usuario logar(@RequestBody UsuarioLoginDto dto) throws Exception {
		return userAuthenticationService.autenticar(dto);
	}
	
	@DeleteMapping("/excluir/{id}")
	public void excluirUsuario(@PathVariable(name = "id") Long id) {
		usuarioService.deletarUsuario(id);
	}
}
