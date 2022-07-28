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

import org.acme.getting.started.dto.ColaboradorDTO;
import org.acme.getting.started.dto.IpDTO;
import org.acme.getting.started.exceptions.NegocialException;
import org.acme.getting.started.models.Colaborador;
import org.acme.getting.started.services.ColaboradorService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
@Path("/colaborador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ColaboradorRest extends ExceptionRest{

	@Inject
	private ColaboradorService service;
	
	private static Logger logger= LoggerFactory.getLogger(ColaboradorRest.class);
	
	@POST
	@Operation(summary = "Criar colaborador", description = "Cria novo colaborador")
	@APIResponse(responseCode = "200", 
	description = "Colaborador", 
	content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ColaboradorDTO.class)) })
	public Response criarColaborador(ColaboradorDTO colaboradorDTO) {
		try {
			logger.info("#### ColaboradorRest -> Recebido CPF: " + colaboradorDTO.getCpf());
			service.criarColaborador(colaboradorDTO);
			return Response.status(Response.Status.CREATED).build();
		}catch(NegocialException e) {
			return tratamentoException(e);
		}
	}
	
	
	@GET
	@Operation(summary = "Listar Colaboradores", description = "Lista todos colaboradores")
	@APIResponse(responseCode = "200", 
	description = "Colaborador", 
	content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Colaborador.class)) })
	public Response listarColaboradores(@QueryParam("resultadosPorPagina") int resultadosPorPagina, @QueryParam("primeiroResultado") int primeiroResultado) {
		return Response.status(Response.Status.OK).entity(service.listarColaboradores(primeiroResultado, resultadosPorPagina)).build();
	}
	
	@GET
	@Operation(summary = "Listar Colaboradores", description = "Lista todos colaboradores")
	@Path("{colaboradorId}")
	@APIResponse(responseCode = "200", 
	description = "Colaborador", 
	content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Colaborador.class)) })
	public Response listarColaboradores(@PathParam("colaboradorId") long colaboradorId) {
		return Response.status(Response.Status.OK).entity(service.buscarColaboradorPorID(colaboradorId)).build();
	}
	
	@PUT
	@Operation(summary = "Criar colaborador", description = "Cria novo colaborador")
	@Path("{colaboradorId}")
	@APIResponse(responseCode = "200", 
	description = "Colaborador", 
	content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Colaborador.class)) })
	public Response atualizarColaborador(Colaborador colaborador, @PathParam("colaboradorId") long colaboradorId) {
		service.atualizarColaborador(colaborador, colaboradorId);
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Operation(summary = "Criar colaborador", description = "Cria novo colaborador")
	@Path("{colaboradorId}")
	@APIResponse(responseCode = "200", 
	description = "Colaborador", 
	content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Colaborador.class)) })
	public Response removerColaborador(@PathParam("colaboradorId") long colaboradorId) {
		service.deletarColaborador(colaboradorId);
		return Response.status(Response.Status.OK).build();
	}
}
