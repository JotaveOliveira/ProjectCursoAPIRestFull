package com.curso.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.curso.security.entities.Usuario;
import com.curso.security.enums.PerfilEnum;

public class JwtUserFactory {

	/**
	 * Converte e gera um JwtUser com base nos dados de um funcionario.
	 * 
	 * @param usuario
	 * @return JwtUser
	 */
	public static JwtUser create(Usuario usuario) {

		return new JwtUser(usuario.getId(), usuario.getEmail(), usuario.getSenha(), 
							mapToGrantedAuthorities(usuario.getPerfil()));
	}

	/**
	 * Converte o perfil do usuario para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfil
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfil) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfil.toString()));
		return authorities;
	}

}
