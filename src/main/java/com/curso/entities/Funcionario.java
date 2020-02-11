package com.curso.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.curso.security.enums.PerfilEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter private Long id;
	
	@Getter @Setter private String nome;
	
	@Getter @Setter private String email;
	
	@Getter @Setter private String senha;
	
	@Getter @Setter private String cpf;
	
	@Getter @Setter private BigDecimal valorHora;
	
	@Getter @Setter private Float qtdHorasTrabalhoDia;
	
	@Getter @Setter private Float qtdHorasAlmoco;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter private  PerfilEnum perfil;
	
	@Getter @Setter private Date dataCriacao;
	
	@Getter @Setter private Date dataAtualizacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Getter @Setter private Empresa empresa;
	
	@OneToMany(mappedBy = "funcionario" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Getter @Setter private List<Lancamento> lancamentos;
	
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}
	
}
