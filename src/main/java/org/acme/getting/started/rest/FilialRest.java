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

import org.acme.getting.started.dto.FilialDTO;
import org.acme.getting.started.exceptions.NegocialException;
import org.acme.getting.started.models.Filial;
import org.acme.getting.started.services.FilialService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@RequestScoped
@Path("/filial")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FilialRest extends ExceptionRest{

	@Inject
	private FilialService service;
	
	@POST
	@Operation(summary = "Criar filial", description = "Cria nova filial")
	@APIResponse(responseCode = "201", description = "Filial", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Filial.class)) })
	public Response criarFilial(FilialDTO filialDTO) {
		try {
			service.criarFilial(filialDTO);
			return Response.status(Response.Status.CREATED).build();
		} catch (NegocialException e) {
			return tratamentoException(e);
		}
	}
	
	@GET
	@Operation(summary = "Listar filiais", description = "Lista todas filiais")
	@APIResponse(responseCode = "200", description = "Filial", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Filial.class)) })
	public Response listarFiliais(@QueryParam("resultadosPorPagina") int numeroResultadoPorPagina, @QueryParam("primeiroResultado") int primeiroResultado) {
		return Response.status(Response.Status.OK).entity(service.listarFiliais(numeroResultadoPorPagina, primeiroResultado)).build();
	}
	
	@GET
	@Operation(summary = "Buscar filial", description = "Busca filial por ID")
	@Path("{filialId}")
	@APIResponse(responseCode = "200", description = "Filial", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Filial.class)) })
	public Response buscarFilialPorID(@PathParam("filialId") long filialId) {
		return Response.status(Response.Status.OK).entity(service.buscarFilialPorID(filialId)).build();
	}
	
	@PUT
	@Operation(summary = "Atualizar filial", description = "Atualiza filial por ID")
	@Path("{filialId}")
	@APIResponse(responseCode = "200", description = "Filial", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Filial.class)) })
	public Response atualizarFilial(Filial filial, @PathParam("filialId") long filialId) {
		service.atualizarFilial(filial, filialId);
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Operation(summary = "Deletar filial", description = "Deleta filial por ID")
	@Path("{filialId}")
	@APIResponse(responseCode = "200", description = "Filial", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Filial.class)) })
	public Response deletarFilial(@PathParam("filialId") long filialId) {
		service.deletarFilial(filialId);
		return Response.status(Response.Status.OK).build();
	}
}
