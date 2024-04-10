package com.contact.finder.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Data
@AllArgsConstructor
public class ContactDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome não pode ser vazio.")
    @Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	private String nome;
	
	
	@NotBlank(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
	@Email(message = "Email inválido.")
	private String email;

	@Pattern(regexp = "\\(?(\\d{2})\\)?[-\\s]?\\d{4,5}[-\\s]?\\d{4}", message = "Número de telefone inválido")
	private String telefone;
	
	private Date data;
	
	@OneToMany
	private List<ListaEnderecoDTO> endereco;
	

}
