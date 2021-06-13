package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Service;

import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UserResgistrationService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
	
	public Usuario registrarComToken(Usuario registrar) {
		registrar.setToken(tokenService.generateToken(registrar));
		return usuarioRepository.save(registrar);
	}
}
