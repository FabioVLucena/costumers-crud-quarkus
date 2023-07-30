package br.com.aeon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CostumerRequestDTO {

	@NotBlank(message = "Name cannot be empty")
	@Size(min = 3, message = "Name must be at least 3 characters")
	private String name;
	
	@NotBlank(message = "Last name cannot be empty")
	@Size(min = 3, message = "Last name must be at least 3 characters")
	private String lastName;
	
	@NotNull(message = "Age cannot be null")
	private Integer age;
	
	@Email(message = "Email is not valid")
	private String email;
	
}
