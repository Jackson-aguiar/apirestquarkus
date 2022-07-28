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
public class FilialDTO {

	private long id;
	
	private String uf;
	
	private String cidade;
	
	private List<ColaboradorDTO> colaborador = new ArrayList<ColaboradorDTO>();

	public FilialDTO(long id, String uf, String cidade) {
		super();
		this.id = id;
		this.uf = uf;
		this.cidade = cidade;
	}
		
}
