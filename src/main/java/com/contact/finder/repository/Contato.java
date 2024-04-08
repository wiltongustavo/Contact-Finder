package com.contact.finder.repository;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.contact.finder.model.ListaEnderecoModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "contato")
public class Contato {

	
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
    
    @Temporal(TemporalType.DATE)
    private Date data;
    
    // anotação @OneToMany para mapear a relação de um para muitos entre..
    @OneToMany(mappedBy = "suaEntidade")
    private List<ListaEnderecoModel> endereco;

	
}
