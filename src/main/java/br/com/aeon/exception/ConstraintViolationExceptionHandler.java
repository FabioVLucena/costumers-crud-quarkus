package br.com.aeon.exception;

import java.util.List;
import java.util.stream.Collectors;

import br.com.aeon.dto.ApiErrorDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		List<String> errorList = exception.getConstraintViolations()
				.stream().map(e -> e.getMessage())
				.collect(Collectors.toList());
		
		ApiErrorDTO error = new ApiErrorDTO(errorList);
		
		return Response.status(Status.BAD_REQUEST).entity(error).build();
	}
}
