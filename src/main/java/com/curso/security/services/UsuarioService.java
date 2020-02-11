package com.curso.security.services;

import java.util.Optional;

import com.curso.security.entities.Usuario;

public interface UsuarioService {

	/**
	 * Busca e retorna um usuário dado um email.
	 * 
	 * @param email
	 * @return
	 */
	Optional<Usuario> buscaPorEmail(String email);
}
