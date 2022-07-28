package org.acme.getting.started.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.getting.started.dto.EquipeDTO;
import org.acme.getting.started.exceptions.NegocialException;
import org.acme.getting.started.models.Equipe;
import org.acme.getting.started.services.EquipeService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@RequestScoped
@Path("/equipe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EquipeRest extends ExceptionRest{
	
	@Inject
	private EquipeService service;
	
	@POST
	@Operation(summary = "Criar equipe", description = "Criar nova equipe")
	@APIResponse(responseCode = "201", description = "Cria nova equipe", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Equipe.class)) })
	public Response criarEquipe(EquipeDTO equipeDTO) {
		try {
			service.criarEquipe(equipeDTO);
			return Response.status(Response.Status.CREATED).build();
		} catch (NegocialException e) {
			return tratamentoException(e);
		}
	}
	
	@GET
	@Operation(summary = "Listar Equipes", description = "Lista todas equipes")
	@APIResponse(responseCode = "200", 
	description = "Equipe", 
	content = {@Content(mediaType = "application/json", 
		schema = @Schema(implementation = Equipe.class)) })
	public Response listarEquipes(@QueryParam("resultadosPorPagina") int resultadosPorPagina, @QueryParam("primeiroResultado") int primeiroResultado) {
		return Response.status(Response.Status.OK).entity(service.listarEquipes(resultadosPorPagina, primeiroResultado)).build();
	}
	
	@GET
	@Operation(summary = "Buscar colaborador por ID", description = "Buscar colaboradores vínculados a uma equipe")
	@Path("{equipeId}/colaboradores")
	@APIResponse(responseCode = "200",
				description = "Equipe",
				content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Equipe.class)) })
	public Response buscarColaboradoresPorEquipe(@PathParam("equipeId") int equipeId) {
		return Response.status(Response.Status.OK).entity(service.buscarColaboradoresPorEquipe(equipeId)).build();
	}
	
	@GET
	@Operation(summary = "Buscar por ID", description = "Buscar equipes por ID")
	@Path("{equipeId}")
	@APIResponse(responseCode = "200",
				description = "Equipe",
				content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Equipe.class)) })
	public Response buscarEquipePorID(@PathParam("equipeId") int equipeId) {
		return Response.status(Response.Status.OK).entity(service.buscarEquipePorId(equipeId)).build();
	} 
	
	@PUT
	@Operation(summary = "Atualizar equipe", description = "Atualizar equipe por id")
	@Path("{equipeId}")
	@APIResponse(responseCode = "200",
				description = "Equipe",
				content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Equipe.class)) })
	public Response atualizarEquipe(Equipe equipe, @PathParam("equipeId") int equipeId) {
		service.atualizarEquipe(equipe, equipeId);
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Operation(summary = "Deletar equipe", description = "Deletar equipe por id")
	@Path("{equipeId}")
	@APIResponse(responseCode = "200", 
				description = "Equipe",
				content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Equipe.class)) })
	public Response deletarEquipe(@PathParam("equipeId") long equipeId) {
		return Response.status(Response.Status.OK).build();
	}

}
