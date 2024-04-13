package com.contact.finder.DTO;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoDTO {
	
	
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
	
	private LocalDate dataNascimento;
	
	private List<EnderecoDTO> endereco;
	

}
