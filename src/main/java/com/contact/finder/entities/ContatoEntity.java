package com.contact.finder.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "contato")
public class ContatoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String email;

	@NotBlank
	private String telefone;

	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
	private List<EnderecoEntity> endereco = new ArrayList<EnderecoEntity>();


	public void setEndereco(List<EnderecoEntity> endereco) {
		endereco.forEach(end -> end.setContact(this));
		this.endereco = endereco;

	}

}
