package org.acme.getting.started.exceptions;

import org.acme.getting.started.enums.MensagensEnum;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class NegocialException extends Exception{

	private int status;
	
	public NegocialException(MensagensEnum mensagem) {
		super(mensagem.getMensagem());
	}
	
	public NegocialException(MensagensEnum exceptionMessageEnum, int status) {
			super(exceptionMessageEnum.getMensagem());
			this.status = status;
	}
}
