package com.curso.security.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.security.entities.Usuario;
import com.curso.security.repositories.UsuarioRepository;
import com.curso.security.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> buscaPorEmail(String email) {
		
		return Optional.ofNullable(this.usuarioRepository.findByEmail(email));
	}

}
