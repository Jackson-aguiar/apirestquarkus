package org.acme.getting.started.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.getting.started.dto.IpDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
@Path("/ip")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IPRest {
	private static Logger logger= LoggerFactory.getLogger(ColaboradorRest.class);
	
	@POST
	@Operation(summary = "GET IP", description = "PEGA O IP")
	@APIResponse(responseCode = "200", 
	description = "IP", 
	content = {@Content(mediaType = "application/json", schema = @Schema(implementation = IpDTO.class)) })
	public Response printarIp(IpDTO ipDTO) {
		logger.info("#### IP REST -> Recebido: " + ipDTO.getIp());
		return Response.status(Response.Status.CREATED).build();
	}
}
