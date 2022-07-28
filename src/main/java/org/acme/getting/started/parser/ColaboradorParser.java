package org.acme.getting.started.parser;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.getting.started.dto.ColaboradorDTO;
import org.acme.getting.started.models.Colaborador;

public class ColaboradorParser {

	public static Colaborador paraEntidade(ColaboradorDTO colaboradorDTO) {
		return new Colaborador(colaboradorDTO.getId(), colaboradorDTO.getNome(), colaboradorDTO.getSobrenome(),
				colaboradorDTO.getEmail(), colaboradorDTO.getCpf(),EquipeParser.paraEntidade(colaboradorDTO.getEquipe()), FilialParser.paraEntidade(colaboradorDTO.getFilial()));
	}
	
	public static ColaboradorDTO paraDTO(Colaborador colaborador) {
		return new ColaboradorDTO(colaborador.getId(), colaborador.getNome(), colaborador.getSobrenome(),
				colaborador.getEmail());
	}
	
	public static List<ColaboradorDTO> paraDTOList(List<Colaborador> colaborador) {
		return colaborador.stream().map(e -> new ColaboradorDTO(e.getId(), e.getNome(), e.getSobrenome(), e.getCpf(),e.getEmail(), EquipeParser.paraDTO(e.getEquipe()), FilialParser.paraDTO(e.getFilial()))).collect(Collectors.toList());
	}

}
