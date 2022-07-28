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
@Table(name = "filial")
@NamedNativeQueries({
	@NamedNativeQuery(name = "CRIAR_FILIAL", 
					query = "INSERT INTO db_empresa.filial (uf, cidade) VALUES (:uf, :cidade)"),
	
	@NamedNativeQuery(name = "LISTAR_FILIAIS", 
					query = "SELECT id, uf, cidade FROM db_empresa.filial", resultClass = Filial.class)	,
	
	@NamedNativeQuery(name = "ATUALIZAR_FILIAL", 
					query = "UPDATE db_empresa.filial SET uf = :uf, cidade = :cidade WHERE id = :id"),
	
	@NamedNativeQuery(name = "TOTAL_FILIAIS",
					query = "SELECT COUNT(*) FROM db_empresa.filial")
	
})
public class Filial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String uf;
	
	@Column
	private String cidade;
	
	@OneToMany(mappedBy = "filial")
	private List<Colaborador> colaborador;
		
}
