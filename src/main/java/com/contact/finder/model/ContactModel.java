package com.contact.finder.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class ContactModel {
	
	private Long id;
	
	
	@NotBlank(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	private String nome;
	
	@NotBlank(message = "Email não pode ser vazio.")
    @Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
    @Email(message="Email inválido.")
	private String email;
	
	@Pattern(regexp = "\\(?(\\d{2})\\)?[-\\s]?\\d{4,5}[-\\s]?\\d{4}", message = "Número de telefone inválido")
	private String telefone;
	
	private Date data;
	
	private List<ListaEnderecoModel> endereco;
	

}
