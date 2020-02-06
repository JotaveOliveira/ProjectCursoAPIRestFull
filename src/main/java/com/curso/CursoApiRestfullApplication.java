package com.curso;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.curso.entities.SenhaUtils;

@SpringBootApplication
public class CursoApiRestfullApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoApiRestfullApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		
		//SenhaUtils senhaUtils = new SenhaUtils();
		
		return retorno -> {
			
			//String senhaEncoded = senhaUtils.gerarBCrypt("123456");
			//System.out.println("Senha encoded: " + senhaEncoded);
			
			//senhaEncoded = senhaUtils.gerarBCrypt("123456");
			//System.out.println("Senha encoded novamente: " + senhaEncoded);
			
			//System.out.println("Senha válida: " + senhaUtils.senhaValida("123456", senhaEncoded));
			
		};
	}

}
