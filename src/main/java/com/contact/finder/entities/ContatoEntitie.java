package com.contact.finder.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
@Entity
@Table(name = "contato")
public class ContatoEntitie {
	
	public ContatoEntitie() {
        
    }

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "nome")
	    private String nome;

	    @Column(name = "email")
	    private String email;

	    @Column(name = "telefone")
	    private String telefone;

	    @Column(name = "data_nascimento")
	    private LocalDate data;
	    
	    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL)
	    private List<EnderecoEntitie> endereco;

	
}
