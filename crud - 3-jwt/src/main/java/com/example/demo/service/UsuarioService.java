package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioLoginDto;
import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public List<Usuario> pegarTodosUsuario() throws Exception{
		return usuarioRepository.findAll();
	}
	
	public void deletarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
}
