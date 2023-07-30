package br.com.aeon.exception;

import br.com.aeon.dto.ApiErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		ApiErrorDTO error = new ApiErrorDTO(exception.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(error).build();
	}
	
}
