package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioLoginDto;
import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

import io.jsonwebtoken.Claims;

@Service
public class UserAuthenticationService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
	
	@Autowired
	public UserAuthenticationService(UsuarioRepository usuarioRepository, TokenService tokenService) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario autenticar(UsuarioLoginDto dados) throws Exception {
		Usuario usuario = usuarioRepository.findByEmail(dados.getEmail());
		
		if(dados.getSenha().equals(usuario.getSenha()) && validate(usuario.getToken())) {
			
			return usuario;
			
		} else {
			
			String novoToken = tokenService.generateToken(usuario);
			usuario.setToken(novoToken);
			usuarioRepository.save(usuario);
			
			return usuario;
		}
	}
	
	
	private boolean validate(String token) throws Exception {
		try {
			String tokenTratado = token.replace("Bearer ", "");
			Claims claims = tokenService.decodeToken(tokenTratado);
			
			if(claims.getExpiration().before(new Date(System.currentTimeMillis())))
				throw new Exception("Token morreu " + claims.getExpiration());
			
			return false;
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
