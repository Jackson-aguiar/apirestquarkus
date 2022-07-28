package org.acme.getting.started.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionDTO<E> {

	private int status;
	private String mensagem;
	private StackTraceElement[] pilha;
	
	public ExceptionDTO(int status, String mensagem, StackTraceElement[] stackTrace) {
		this.status = status;
		this.mensagem = mensagem;
		this.pilha = stackTrace;
	}
	
}
