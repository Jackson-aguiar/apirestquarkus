package org.acme.getting.started.pagination;

import java.util.List;

import org.acme.getting.started.dto.ColaboradorDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorPagination {

	private List<ColaboradorDTO> data;

	private long numeroTotalDeResultados = 0;

	private long primeiroResultado = 0;
}
