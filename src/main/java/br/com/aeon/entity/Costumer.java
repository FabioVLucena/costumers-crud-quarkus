package br.com.aeon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Costumer {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String lastName;
	
	private Integer age;
	
	private String email;
	
}
