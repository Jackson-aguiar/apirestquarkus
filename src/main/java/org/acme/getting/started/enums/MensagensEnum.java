package org.acme.getting.started.enums;

public enum MensagensEnum {

	ERRO_APLICACAO("Erro ao executar aplicação"),
	ERRO_COLABORADOR_VINCULADO("O colaborador encontra-se vínculado a uma equipe"),
	ERRO_EQUIPE_VINCULADA("A equipe contém colaboradores vínculados"),
	ERRO_FILIAL_VINCULADA("A filial contém colaboradores vínculados"),
	CAMPO_NOME_VAZIO("O campo nome está vazio"),
	CAMPO_SOBRENOME_VAZIO("O campo sobrenome está vazio"),
	CAMPO_EMAIL_VAZIO("O campo email está vazio"),
	CAMPO_CPF_VAZIO("O campo cpf está vazio"),
	CAMPO_UF_VAZIO("O campo uf está vazio"),
	CAMPO_CIDADE_VAZIO("O campo cidade está vazio"),
	TAMANHO_CAMPO_NOME("O  campo nome excede o limite de 250 caracteres"),
	TAMANHO_CAMPO_SOBRENOME("O  campo sobrenome excede o limite de 250 caracteres"),
	TAMANHO_CAMPO_EMAIL("O  campo email excede o limite de 100 caracteres"),
	TAMANHO_CAMPO_CPF("O  campo cpf deve conter 11 caracteres"),
	TAMANHO_CAMPO_UF("O campo uf deve conter 2 caracteres"),
	TAMANHO_CAMPO_CIDADE("O campo cidade excede o limite de 250 caracteres");
	
	private String mensagem;
	
	MensagensEnum(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	
}
