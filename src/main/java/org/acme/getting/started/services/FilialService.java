package org.acme.getting.started.services;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.acme.getting.started.dao.FilialDAO;
import org.acme.getting.started.dto.FilialDTO;
import org.acme.getting.started.enums.MensagensEnum;
import org.acme.getting.started.exceptions.NegocialException;
import org.acme.getting.started.models.Filial;
import org.acme.getting.started.pagination.FilialPagination;
import org.acme.getting.started.parser.FilialParser;
import org.acme.getting.started.utils.Utils;
import org.apache.http.HttpStatus;

@RequestScoped
public class FilialService {

	@Inject
	private FilialDAO filialDAO;
	
	private static final int TAMANHO_CAMPO_UF = 2;
	private static final int TAMANHO_CAMPO_CIDADE = 250;
	
	public int criarFilial(FilialDTO filialDTO) throws NegocialException {
		validarUf(filialDTO);
		validarCidade(filialDTO);
		
		Filial filial = FilialParser.paraEntidade(filialDTO);
		return filialDAO.criarFilial(filial);
	}
	
	public FilialPagination listarFiliais(int resultadosPorPagina, int primeiroResultado){
		//Valida o numero de resultados por pagina, se o numero for invalido é definido o numero 15 por default
		resultadosPorPagina = Utils.validarNumeroDeResultadosPorPaginas(resultadosPorPagina);
		
		//numeroRealDaPagina -= 1;
		//Retorna o resultado para uma lista do tipo filial
		List<Filial> filial=  filialDAO.listarFiliais(resultadosPorPagina, primeiroResultado);
		
		//Converte a lista para um DTO
		List<FilialDTO> filialDTO = FilialParser.paraDTOList(filial);
		
		//Calcula o total de paginas numeroTotalDeResultados / numeroDeResultadosPorPagina = resultado;
		int numeroTotalDeResultados = filialDAO.numeroTotalDeResultados();
		
		//Cria uma nova instância de filialPagination com os dados definidos;
		FilialPagination filialPagination = new FilialPagination(filialDTO, numeroTotalDeResultados, primeiroResultado);
		
		return filialPagination;
		
	}
	
	public Filial buscarFilialPorID(long filialId) {
		return filialDAO.buscarFilialPorID(filialId);
	}
	
	public int atualizarFilial(Filial filial, long filialId) {
		return filialDAO.atualizarFilial(filial, filialId);
	}
	
	public void deletarFilial(long filialId) {
		
		filialDAO.deletarFilial(filialId);
	}
	
	/*
	 * VALIDAÇÕES
	 */
	private void validarUf(FilialDTO filialDTO) throws NegocialException{
		boolean ufVazio = Objects.isNull(filialDTO.getUf()) || filialDTO.getUf().isEmpty();
		boolean tamanhoInvalido = filialDTO.getUf().length() != TAMANHO_CAMPO_UF;
		
		if(ufVazio) {
			throw new NegocialException(MensagensEnum.CAMPO_UF_VAZIO, HttpStatus.SC_BAD_REQUEST);
		}else if(tamanhoInvalido) {
			throw new NegocialException(MensagensEnum.TAMANHO_CAMPO_UF, HttpStatus.SC_BAD_REQUEST);
		}
	}
	
	private void validarCidade(FilialDTO filialDTO) throws NegocialException{
		boolean cidadeVazio = Objects.isNull(filialDTO.getCidade()) || filialDTO.getCidade().isEmpty();
		boolean tamanhoInvalido = filialDTO.getCidade().length() > TAMANHO_CAMPO_CIDADE;
		
		if(cidadeVazio) {
			throw new NegocialException(MensagensEnum.CAMPO_CIDADE_VAZIO, HttpStatus.SC_BAD_REQUEST);
		}else if(tamanhoInvalido) {
			throw new NegocialException(MensagensEnum.TAMANHO_CAMPO_CIDADE, HttpStatus.SC_BAD_REQUEST);
		}
	}
}
