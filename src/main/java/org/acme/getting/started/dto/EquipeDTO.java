package org.acme.getting.started.dto;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequestScoped
public class EquipeDTO {

	
	private long id;
	
	private String nome;
	
	private List<ColaboradorDTO> colaborador = new ArrayList<ColaboradorDTO>();

	public EquipeDTO(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
}
