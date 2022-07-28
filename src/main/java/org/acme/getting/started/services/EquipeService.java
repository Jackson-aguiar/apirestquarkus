package org.acme.getting.started.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.acme.getting.started.dao.EquipeDAO;
import org.acme.getting.started.dto.EquipeDTO;
import org.acme.getting.started.enums.MensagensEnum;
import org.acme.getting.started.exceptions.NegocialException;
import org.acme.getting.started.models.Equipe;
import org.acme.getting.started.pagination.EquipePagination;
import org.acme.getting.started.parser.ColaboradorParser;
import org.acme.getting.started.parser.EquipeParser;
import org.acme.getting.started.utils.Utils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class EquipeService {

	@Inject
	private EquipeDAO equipeDAO;
	
	private static final int TAMANHO_CAMPO_NOME = 250;
	
	private Logger logger = LoggerFactory.getLogger(EquipeService.class);
	
	public int criarEquipe(EquipeDTO equipeDTO) throws NegocialException {
		validarNome(equipeDTO);
		
		Equipe equipe = EquipeParser.paraEntidade(equipeDTO);
		
		return equipeDAO.criarEquipe(equipe);
	}
	
	public EquipePagination listarEquipes(int resultadosPorPagina, int primeiroResultado){
		logger.info("### listarEquipes ###");
		
		/*
		 * Valida o numero de resultados por página
		 */
		resultadosPorPagina = Utils.validarNumeroDeResultadosPorPaginas(resultadosPorPagina);
		
		List<Equipe> equipe =  equipeDAO.listarEquipes(resultadosPorPagina, primeiroResultado);
		
		List<EquipeDTO> equipeDTO = EquipeParser.paraDTOList(equipe);
		
		int numeroTotalDeResultados = equipeDAO.numeroTotalDeResultados();
						
		EquipePagination paginacaoDTO = new EquipePagination(equipeDTO, numeroTotalDeResultados, primeiroResultado);
		
		return paginacaoDTO;
	}
	
	public EquipeDTO buscarEquipePorId(long equipeId) {
		Optional<Equipe> e = equipeDAO.buscarEquipePorId(equipeId);
		
		Equipe equipe = e.get();
		EquipeDTO equipeDTO = EquipeParser.paraDTO(equipe);
		
		return equipeDTO;
	}
	
	public EquipeDTO buscarColaboradoresPorEquipe(long equipeId){
		Optional<Equipe> e = equipeDAO.buscarColaboradoresPorEquipe(equipeId);
		
		if(e.isEmpty()) {
			throw new NotFoundException();
		}
		
		Equipe equipe = e.get();
		EquipeDTO equipeDTO = EquipeParser.paraDTO(equipe);
		
		if(equipe.getColaborador() != null) {
			equipeDTO.setColaborador(equipe.getColaborador().stream().map(c -> ColaboradorParser.paraDTO(c) ).collect(Collectors.toList()));
		}
		
		return equipeDTO;
	}
	
	public int atualizarEquipe(Equipe equipe, long equipeId) {
		return equipeDAO.atualizarEquipe(equipe, equipeId);
	}
	
	/*
	 * VALIDAÇÕES
	 */
	
	private void validarNome(EquipeDTO equipeDTO) throws NegocialException {
		boolean nomeVazio = Objects.isNull(equipeDTO.getNome()) || equipeDTO.getNome().isEmpty();
		boolean tamanhoInvalido = equipeDTO.getNome().length() > TAMANHO_CAMPO_NOME;
		
		if(nomeVazio) {
			throw new NegocialException(MensagensEnum.CAMPO_NOME_VAZIO, HttpStatus.SC_BAD_REQUEST);
		}else if(tamanhoInvalido) {
			throw new NegocialException(MensagensEnum.TAMANHO_CAMPO_NOME, HttpStatus.SC_BAD_REQUEST);
		}
	}
	
	
}
