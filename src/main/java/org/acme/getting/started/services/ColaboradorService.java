package org.acme.getting.started.services;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.acme.getting.started.dao.ColaboradorDAO;
import org.acme.getting.started.dto.ColaboradorDTO;
import org.acme.getting.started.enums.MensagensEnum;
import org.acme.getting.started.exceptions.NegocialException;
import org.acme.getting.started.models.Colaborador;
import org.acme.getting.started.pagination.ColaboradorPagination;
import org.acme.getting.started.parser.ColaboradorParser;
import org.acme.getting.started.utils.Utils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class ColaboradorService {

	private static Logger logger = LoggerFactory.getLogger(ColaboradorService.class);
	
	private static final int TAMANHO_CAMPO_NOME = 250;
	private static final int TAMANHO_CAMPO_SOBRENOME = 250;
	private static final int TAMANHO_CAMPO_EMAIL = 100;
	private static final int TAMANHO_CAMPO_CPF = 11;

	private static boolean ATIVO = true;
	
	@Inject
	private ColaboradorDAO colaboradorDAO;

	public void criarColaborador(ColaboradorDTO colaboradorDTO) throws NegocialException{

		validarNome(colaboradorDTO);
		validarSobrenome(colaboradorDTO);
		validarEmail(colaboradorDTO);
		validarCpf(colaboradorDTO);
		
		colaboradorDAO.criarColaborador(colaboradorDTO);
		
	}

	public ColaboradorPagination listarColaboradores(int primeiroResultado, int resultadosPorPagina) {

		resultadosPorPagina = Utils.validarNumeroDeResultadosPorPaginas(resultadosPorPagina);

		List<Colaborador> colaborador = colaboradorDAO.listarColaboradores(resultadosPorPagina, primeiroResultado, ATIVO);

		List<ColaboradorDTO> colaboradorDTO = ColaboradorParser.paraDTOList(colaborador);

		int numeroTotalDeResultados = colaboradorDAO.numeroTotalDeResultados(ATIVO);

		ColaboradorPagination colaboradorPagination = new ColaboradorPagination(colaboradorDTO, numeroTotalDeResultados, primeiroResultado);

		logger.info("#### ColaboradorService -> listarColaboradores ####");
		return colaboradorPagination;
	}

	public Colaborador buscarColaboradorPorID(long colaboradorId) {
		logger.info("#### ColaboradorService -> buscarColaboradorPorID  -> " + colaboradorId);
		return colaboradorDAO.buscarColaboradorPorID(colaboradorId);
	}

	public int atualizarColaborador(Colaborador colaborador, long colaboradorId) {
		logger.info("#### ColaboradorService -> atualizarColaborador -> " + colaboradorId);
		return colaboradorDAO.atualizarColaborador(colaborador, colaboradorId);
	}

	public void deletarColaborador(long colaboradorId) {
		logger.info("#### ColaboradorService -> deletarColaborador -> " + colaboradorId);
		colaboradorDAO.deletarColaborador(colaboradorId);
	}
	
	/*
	 * #########  VALIDAÇÕES ##########
	 */
	
	private void validarNome(ColaboradorDTO colaboradorDTO) throws NegocialException {
		boolean nomeVazio = Objects.isNull(colaboradorDTO.getNome()) || colaboradorDTO.getNome().isEmpty();
		boolean tamanhoInvalido = colaboradorDTO.getNome().length() > TAMANHO_CAMPO_NOME;
		
		if(nomeVazio) {
			throw new NegocialException(MensagensEnum.CAMPO_NOME_VAZIO, HttpStatus.SC_BAD_REQUEST);
		}else if(tamanhoInvalido) {
			throw new NegocialException(MensagensEnum.TAMANHO_CAMPO_NOME, HttpStatus.SC_BAD_REQUEST);
		}
	}
	
	private void validarSobrenome(ColaboradorDTO colaboradorDTO) throws NegocialException {
		boolean sobrenomeVazio = Objects.isNull(colaboradorDTO.getSobrenome()) || colaboradorDTO.getSobrenome().isEmpty();
		boolean tamanhoInvalido = colaboradorDTO.getSobrenome().length() > TAMANHO_CAMPO_SOBRENOME;
		
		if(sobrenomeVazio) {
			throw new NegocialException(MensagensEnum.CAMPO_SOBRENOME_VAZIO, HttpStatus.SC_BAD_REQUEST);
		}else if(tamanhoInvalido) {
			throw new NegocialException(MensagensEnum.TAMANHO_CAMPO_SOBRENOME, HttpStatus.SC_BAD_REQUEST);
		}
	}
	
	private void validarEmail(ColaboradorDTO colaboradorDTO) throws NegocialException {
		boolean emailVazio = Objects.isNull(colaboradorDTO.getEmail()) || colaboradorDTO.getEmail().isEmpty();
		boolean tamanhoInvalido = colaboradorDTO.getEmail().length() > TAMANHO_CAMPO_EMAIL;
		
		if(emailVazio) {
			throw new NegocialException(MensagensEnum.CAMPO_EMAIL_VAZIO, HttpStatus.SC_BAD_REQUEST);
		}else if(tamanhoInvalido) {
			throw new NegocialException(MensagensEnum.TAMANHO_CAMPO_EMAIL, HttpStatus.SC_BAD_REQUEST);
		}
	}
	
	private void validarCpf(ColaboradorDTO colaboradorDTO) throws NegocialException {
		boolean cpfVazio = Objects.isNull(colaboradorDTO.getCpf()) || colaboradorDTO.getCpf().isEmpty();
		boolean tamanhoInvalido = colaboradorDTO.getCpf().length() != TAMANHO_CAMPO_CPF;
		
		if(cpfVazio) {
			throw new NegocialException(MensagensEnum.CAMPO_CPF_VAZIO, HttpStatus.SC_BAD_REQUEST);
		}else if(tamanhoInvalido) {
			throw new NegocialException(MensagensEnum.TAMANHO_CAMPO_CPF, HttpStatus.SC_BAD_REQUEST);
		}
	}
}
