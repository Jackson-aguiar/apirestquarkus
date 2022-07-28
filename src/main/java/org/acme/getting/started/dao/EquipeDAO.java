package org.acme.getting.started.dao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.acme.getting.started.models.Equipe;

@RequestScoped
public class EquipeDAO {
	
	@PersistenceContext
	EntityManager em;
	
	//Criar nova equipe
	@Transactional
	public int criarEquipe(Equipe equipe) {
		String namedQuery = "CRIAR_EQUIPE";
		
		Query query = em.createNamedQuery(namedQuery);
		
		query.setParameter("nome", equipe.getNome());
		return query.executeUpdate();
	}
	
	//Atualizar Equipe
	@Transactional
	public int atualizarEquipe(Equipe equipe, long equipeId) {
		String namedQuery = "ATUALIZAR_EQUIPE";
		
		Query query = em.createNamedQuery(namedQuery);
		
		query.setParameter("nome", equipe.getNome());
		query.setParameter("id", equipeId);
		
		return query.executeUpdate();
	}
	
	//Listar todas equipes
	public List<Equipe> listarEquipes(int resultadosPorPagina, int primeiroResultado) {
		String namedQuery = "LISTAR_EQUIPES";
		
		TypedQuery<Equipe> query = em.createNamedQuery(namedQuery, Equipe.class);
		
		return query.setMaxResults(resultadosPorPagina)
		.setFirstResult(primeiroResultado)
		.getResultList();
	}
	
	public int numeroTotalDeResultados() {
		String namedQuery = "TOTAL_EQUIPES";
		
		Query query = em.createNamedQuery(namedQuery);
	
		int total = ((Number) query.getSingleResult()).intValue();
			
		return total;
	}
	
	//Buscar equipe por id
	public Optional<Equipe> buscarEquipePorId(long equipeId){
		 try {
			 Equipe equipe = em.find(Equipe.class, equipeId);
			 
			 return Optional.of(equipe);
		 }catch(NoResultException e) {
			 throw new NoResultException();
		 }
	}
	
	//Buscar colaboradores por equipe
	public Optional<Equipe> buscarColaboradoresPorEquipe(long equipeId) {
		
		TypedQuery<Equipe> query = em.createQuery("SELECT equipe FROM Equipe equipe LEFT JOIN FETCH equipe.colaborador WHERE equipe.id = :id", Equipe.class);
		
		query.setParameter("id", equipeId);
		try {
			return Optional.of(query.getSingleResult());
		}catch(NoResultException e) {
			//this.logger.info("Equipe não encontrada para o id: " + equipeId);
		}
		
		return Optional.empty();
	}
	
	@Transactional
	public void deletarEquipe(Equipe equipe) {
		em.remove(equipe);
	}
}
