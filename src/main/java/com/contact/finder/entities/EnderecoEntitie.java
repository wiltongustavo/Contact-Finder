package com.contact.finder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "endereco")
public class EnderecoEntitie {
	
	public EnderecoEntitie(){
		
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "rua")
    private String rua;

	@Column(name = "numero")
    private String numero;
	
	@Column(name = "cep")
    private String cep;

    @ManyToOne
    @JsonIgnore
    private ContatoEntitie contato;
}
