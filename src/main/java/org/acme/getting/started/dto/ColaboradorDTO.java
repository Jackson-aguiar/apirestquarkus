package org.acme.getting.started.dto;

import javax.enterprise.context.RequestScoped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequestScoped
public class ColaboradorDTO {

	private long id;
	
	private String nome;
	
	private String sobrenome;
	
	private String cpf;
	
	private String email;
	
	private EquipeDTO equipe;

	private FilialDTO filial;

	public ColaboradorDTO(long id, String nome, String sobrenome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
	}
	
	
}
