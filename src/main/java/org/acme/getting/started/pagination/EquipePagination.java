package org.acme.getting.started.pagination;

import java.util.List;

import org.acme.getting.started.dto.EquipeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipePagination{

	private List<EquipeDTO> data;

	private long numeroTotalDeResultados = 0;
	
	private long primeiroResultado = 0;

}
