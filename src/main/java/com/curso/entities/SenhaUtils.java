package com.curso.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtils {

	/**
	 * Gera um hash utilizando o BCrypt.
	 * 
	 * @param senha
	 * @return String
	 */
	public String gerarBCrypt(String senha) {
		if(senha.equals(null))
			return senha;
		
		BCryptPasswordEncoder bCryptEncoder  = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(senha);
	}
	
	/**
	 * Verifica se a senha é válida.
	 * 
	 * @param senha
	 * @param senhaEncoded
	 * @return Boolean
	 */
	
	public Boolean senhaValida(String senha, String senhaEncoded) {
		
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		
		return bCryptEncoder.matches(senha, senhaEncoded);
	}
}
