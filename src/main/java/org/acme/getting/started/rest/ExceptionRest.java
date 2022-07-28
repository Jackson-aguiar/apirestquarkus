package org.acme.getting.started.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.getting.started.dto.ExceptionDTO;
import org.acme.getting.started.exceptions.NegocialException;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExceptionRest {

	public Response tratamentoException(NegocialException e) {
		ExceptionDTO<Exception> exceptionDTO = new ExceptionDTO<Exception>(e.getStatus(), e.getMessage(), e.getStackTrace());
		
		return Response.status(e.getStatus()).entity(exceptionDTO).build();
	}
}
