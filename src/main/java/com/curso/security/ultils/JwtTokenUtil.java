package com.curso.security.ultils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_CREATED = "created";
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	
	/**
	 * 
	 * Obtém o username(email) contido no token JWT.
	 * 
	 * @param token
	 * @return String
	 */
	public String getUsernameFromToken(String token) {
		String username;
		
		try {
			
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
			
		}catch(Exception ex) {
			
			username = null;
		}
		
		return username;
	}

	/**
	 * Retorna a data de expiracao de um token JWT.
	 * 
	 * @param token
	 * @return Date
	 */
	
	public Date getExpirationDateFromToken(String token) {
		
		Date expiration;
		
		try {
			
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
			
		}catch(Exception ex) {
			
			expiration = null;
		}
		
		return expiration;
	}
	
	
	/**
	 * 
	 * Cria um novo token.
	 * 
	 * @param token
	 * @return String
	 */
	
	public String refreshToken(String token) {
		
		String refreshedToken;
		
		try {
			
			Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = gerarToken(claims);
			
		}catch(Exception ex) {
			
			refreshedToken = null;
		}
		
		return refreshedToken;
	}
	
	/**
	 * Verifica e retorna se um token JWT é valido.
	 * 
	 * @param token
	 * @return boolean
	 */
	
	public 	Boolean tokenValido(String token) {
		
		return !tokenExpirado(token);
	}
	
	/**
	 * Retorna um novo token com base nos dados do usuários.
	 * 
	 * @param userDetails
	 * @return
	 */
	public String obterToken(UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		claims.put(CLAIM_KEY_CREATED, new Date());
		
		return gerarToken(claims);
	}

	/**
	 * Realiza o parse do token para extrair as informações contidas no corpo dele.
	 * 
	 * @param token
	 * @return Claims
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception ex) {
			claims = null;
		}
		
		return claims;
	}
	
	/**
	 * Gera um novo token contendo os dados fornecidos.
	 * 
	 * @param claims
	 * @return String
	 */
	private String gerarToken(Map<String, Object> claims) {
		
		return Jwts.builder().setClaims(claims).setExpiration(gerarDataExpiracao())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	

	/**
	 * 
	 * Verifica se um token está expirado.
	 * 
	 * @param token
	 * @return Boolean
	 */
	private Boolean tokenExpirado(String token) {
		
		Date dataExpiracao = this.getExpirationDateFromToken(token);
		
		if(dataExpiracao.equals(null))
			return false;
		
		return dataExpiracao.before(new Date());
	}
	
	/**
	 * Retorna a data de expiracao com base na data atual.
	 * 
	 * @return Date
	 */
	
	private Date gerarDataExpiracao() {

		return new Date(System.currentTimeMillis() + expiration * 1000);
	}
	
}
