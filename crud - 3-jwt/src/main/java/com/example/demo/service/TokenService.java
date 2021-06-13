package com.example.demo.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.models.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private static final String KEY = "String Aleatoria Secreta";
	
	private static final long EXPIRATIONTIME = 1800000;
	
	
	public String generateToken(Usuario usuario) {
		return Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject(usuario.getNome())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS256, KEY)
				.compact();
	}
	
	public Claims decodeToken(String token) {
		return Jwts.parser()
				.setSigningKey(KEY)
				.parseClaimsJws(token)
				.getBody();
	}
}
