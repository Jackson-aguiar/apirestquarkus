package org.acme.getting.started.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.acme.getting.started.models.Filial;

@RequestScoped
public class FilialDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public int criarFilial(Filial filial) {
		String namedQuery = "CRIAR_FILIAL";
		
		Query query = em.createNamedQuery(namedQuery);
		
		query.setParameter("uf", filial.getUf());
		query.setParameter("cidade", filial.getCidade());
		
		return query.executeUpdate();
	}
	
	public List<Filial> listarFiliais(int resultadosPorPagina, int primeiroResultado){
		String namedQuery = "LISTAR_FILIAIS";
		
		TypedQuery<Filial> query = em.createNamedQuery(namedQuery, Filial.class);
		
		return query
				.setMaxResults(resultadosPorPagina)
				.setFirstResult(primeiroResultado)
				.getResultList();
	}
	
	public int numeroTotalDeResultados() {
		String namedQuery = "TOTAL_FILIAIS";
		
		Query query = em.createNamedQuery(namedQuery);
		
		int total = ((Number) query.getSingleResult()).intValue();
		
		return total;
	}
	
	public Filial buscarFilialPorID(long filialId){
		Filial filial = em.find(Filial.class, filialId);
		return filial;
	}
	
	@Transactional
	public int atualizarFilial(Filial filial, long filialId) {
		String namedQuery = "ATUALIZAR_FILIAL";
		
		Query query = em.createNamedQuery(namedQuery);
		
		query.setParameter("id", filialId);
		query.setParameter("uf", filial.getUf());
		query.setParameter("cidade", filial.getCidade());
		
		return query.executeUpdate();
	}
	
	@Transactional
	public void deletarFilial(long filialId) {
		Filial filial = this.buscarFilialPorID(filialId);
		em.remove(filial);
	}
}
