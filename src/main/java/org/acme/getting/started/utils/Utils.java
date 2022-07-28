package org.acme.getting.started.utils;

import java.util.Arrays;
import java.util.List;

import org.acme.getting.started.services.EquipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	
	private static Logger logger = LoggerFactory.getLogger(EquipeService.class);
	
	/*
	 * Constantes de Paginação 
	 */
	private static final List<Integer> NUMERO_RESULTADOS_POSSIVEIS = Arrays.asList(15,30,50);
	private static final int NUMERO_DE_RESULTADOS_PADRAO = 15;
	
	
	public static int calcularNumeroDePaginas(int numeroTotalDeElementos, int elementosPorPagina) {
		int total = (int) Math.ceil(((float)numeroTotalDeElementos / (float)elementosPorPagina));
		
		StringBuilder str = new StringBuilder("CalcularNumeroDePaginas.class ->")
				.append(" numeroTotalDeElementos: " + numeroTotalDeElementos)
				.append("-> elementosPorPagina: " + elementosPorPagina)
				.append("-> total: " + total);
		
		logger.info(str.toString());
		
		return total;
	}
	
	public static int validarNumeroDeResultadosPorPaginas(int numeroDeResultados) {
		if(NUMERO_RESULTADOS_POSSIVEIS.contains(numeroDeResultados)) {
			return numeroDeResultados;
		}else {
			return NUMERO_DE_RESULTADOS_PADRAO;
		}
	}
	
	public static int numeroRealDaPagina(int paginaAtual) {		
		return paginaAtual -= 1;
	}
}
