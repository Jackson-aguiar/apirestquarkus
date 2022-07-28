package org.acme.getting.started.parser;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.getting.started.dto.EquipeDTO;
import org.acme.getting.started.models.Equipe;

public class EquipeParser {

	public static Equipe paraEntidade(EquipeDTO equipeDTO) {
		return new Equipe(equipeDTO.getId(), equipeDTO.getNome());
	}
	
	public static EquipeDTO paraDTO(Equipe equipe) {
		return new EquipeDTO(equipe.getId(), equipe.getNome());
	}
	
	public static List<EquipeDTO> paraDTOList(List<Equipe> equipe){
		return equipe.stream().map(e -> new EquipeDTO(e.getId(), e.getNome())).collect(Collectors.toList());
	}
}
