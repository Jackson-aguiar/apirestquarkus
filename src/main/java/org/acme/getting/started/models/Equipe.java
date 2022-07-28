package org.acme.getting.started.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipe")
@NamedNativeQueries({
	@NamedNativeQuery(name = "CRIAR_EQUIPE", 
					query = "INSERT INTO db_empresa.equipe (nome) VALUES (:nome)"),
	
	@NamedNativeQuery(name = "LISTAR_EQUIPES", 
					query = "SELECT id, nome FROM db_empresa.equipe", resultClass = Equipe.class),
	
	@NamedNativeQuery(name = "NUMERO_DE_EQUIPES", query = "SELECT COUNT(*) FROM db_empresa.equipe"),
	
	@NamedNativeQuery(name = "ATUALIZAR_EQUIPE", 
					query = "UPDATE db_empresa.equipe SET nome = :nome WHERE id = :id", resultClass = Equipe.class),
	
	@NamedNativeQuery(name = "TOTAL_EQUIPES",
					query = "SELECT COUNT(*) FROM db_empresa.equipe")
})
public class Equipe implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String nome;
	
	@OneToMany(mappedBy = "equipe")
	private List<Colaborador> colaborador;
	
	public Equipe(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

}
