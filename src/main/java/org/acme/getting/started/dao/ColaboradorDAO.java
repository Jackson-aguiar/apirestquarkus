package org.acme.getting.started.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.acme.getting.started.dto.ColaboradorDTO;
import org.acme.getting.started.models.Colaborador;


@RequestScoped
public class ColaboradorDAO {
	
	@PersistenceContext
	EntityManager em;
		
	//CRIAR NOVO COLABORADOR
	@Transactional
	public int criarColaborador(ColaboradorDTO colaboradorDTO) {
		String namedQuery = "CRIAR_COLABORADOR";
		
		Query query = em.createNamedQuery(namedQuery);
		
		query.setParameter("nome", colaboradorDTO.getNome());
		query.setParameter("sobrenome", colaboradorDTO.getSobrenome());
		query.setParameter("email", colaboradorDTO.getEmail());
		query.setParameter("cpf", colaboradorDTO.getCpf());
		query.setParameter("filial_id", colaboradorDTO.getFilial().getId());
		query.setParameter("equipe_id", colaboradorDTO.getEquipe().getId());
		
		return query.executeUpdate();
	}
	
	//LISTAR TODOS COLABORADORES
	public List<Colaborador> listarColaboradores(int resultadosPorPagina, int primeiroResultado, boolean ativo){
		String namedQuery = "LISTAR_COLABORADORES";
		
		TypedQuery<Colaborador> query = em.createNamedQuery(namedQuery, Colaborador.class);
		
		return query
				.setParameter("ativo", ativo)
				.setMaxResults(resultadosPorPagina)
				.setFirstResult(primeiroResultado)
				.getResultList();
	}
	
	public int numeroTotalDeResultados(boolean ativo) {
		String namedQuery = "NUMERO_COLABORADORES";
		
		Query query = em.createNamedQuery(namedQuery);
		
		query.setParameter("ativo", ativo);
	
		int total = ((Number) query.getSingleResult()).intValue();
			
		return total;
	}
	
	//BUSCAR COLABORADOR POR ID
	public Colaborador buscarColaboradorPorID(long colaboradorId) {
		return em.find(Colaborador.class, colaboradorId);
	}
	
	//ATUALIZAR COLABORADOR
	@Transactional
	public int atualizarColaborador(Colaborador colaborador, long colaboradorId) {
		String namedQuery = "ATUALIZAR_COLABORADOR";
		
		Query query = em.createNamedQuery(namedQuery);
		
		query.setParameter("nome", colaborador.getNome());
		query.setParameter("sobrenome", colaborador.getSobrenome());
		query.setParameter("cpf", colaborador.getCpf());
		query.setParameter("email", colaborador.getEmail());
		query.setParameter("id", colaboradorId);
		
		return query.executeUpdate();
	}
	
	//DELETAR COLABORADOR
	@Transactional
	public void deletarColaborador(long colaboradorId) {
		String namedQuery = "DELETAR_COLABORADOR";
		
		Query query = em.createNamedQuery(namedQuery);
		
		query
		.setParameter("id", colaboradorId)
		.executeUpdate();
		
	}
}
