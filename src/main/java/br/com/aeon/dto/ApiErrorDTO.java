package br.com.aeon.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrorDTO {
	
	@Getter
	private List<String> errors;
	
	public ApiErrorDTO(List<String> errorList) {
		this.errors = errorList;
	}

	public ApiErrorDTO(String error) {
		this.errors = Arrays.asList(error);
	}

}
