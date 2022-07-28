package org.acme.getting.started.parser;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.getting.started.dto.FilialDTO;
import org.acme.getting.started.models.Filial;

public class FilialParser {

	public static Filial paraEntidade(FilialDTO filialDTO) {
		return new Filial(filialDTO.getId(), filialDTO.getUf(), filialDTO.getCidade(), null);
	}
	
	public static FilialDTO paraDTO(Filial filial) {
		return new FilialDTO(filial.getId(), filial.getUf(), filial.getCidade());
	}
	
	public static List<FilialDTO> paraDTOList(List<Filial> filial) {
		return filial.stream().map(f -> new FilialDTO(f.getId(), f.getUf(), f.getCidade())).collect(Collectors.toList());
	}
}
