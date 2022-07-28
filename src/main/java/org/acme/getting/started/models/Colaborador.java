package org.acme.getting.started.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "colaborador")
@NamedNativeQueries({
	@NamedNativeQuery(name = "CRIAR_COLABORADOR", 
					query = "INSERT INTO db_empresa.colaborador (nome, sobrenome, cpf, email, filial_id, equipe_id) VALUES (:nome, :sobrenome, :cpf, :email, :filial_id, :equipe_id)"),
	
	@NamedNativeQuery(name = "LISTAR_COLABORADORES", 
					query = "SELECT id, nome, sobrenome, cpf, email, filial_id, equipe_id FROM db_empresa.colaborador WHERE ativo = :ativo", resultClass = Colaborador.class),
	
	@NamedNativeQuery(name = "ATUALIZAR_COLABORADOR", 
					query = "UPDATE db_empresa.colaborador SET nome = :nome, sobrenome = :sobrenome, cpf = :cpf, email = :email WHERE id = :id"),
	
	@NamedNativeQuery(name = "NUMERO_COLABORADORES", 
					query = "SELECT COUNT(*) FROM db_empresa.colaborador WHERE ativo = :ativo"),
	
	@NamedNativeQuery(name = "DELETAR_COLABORADOR", 
					query = "UPDATE db_empresa.colaborador SET ativo = false WHERE id = :id")
})
public class Colaborador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String nome;
	
	@Column
	private String sobrenome;
	
	@Column
	private String cpf;
	
	@Column
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipe_id", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Equipe equipe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "filial_id", referencedColumnName = "id")
	private Filial filial;


}