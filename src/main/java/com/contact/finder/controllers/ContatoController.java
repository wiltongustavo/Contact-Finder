package com.contact.finder.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import com.contact.finder.entities.ContatoEntitie;
import com.contact.finder.service.ContactService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(value = "/contatos")
public class ContatoController {
	@Autowired
	private ContactService contactService;
	
	 
	@GetMapping
	public List<ContatoEntitie> listarTodos(){
		log.info("Iniciando a listagem de todos os contatos ");
		return contactService.getAllContact();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContatoEntitie> buscarContato(@PathVariable Long id){
		Optional<ContatoEntitie> contact = contact = contactService.getContactById(id);
		log.info("Buscando um unico contato através do id: " + id);
		return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<ContatoEntitie>criarContato(@Valid @RequestBody ContatoEntitie contactDTO) {
		ContatoEntitie createContact = contactService.createContact(contactDTO);
		log.info("Criando um contato");
		return ResponseEntity.status(HttpStatus.CREATED).body(createContact);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ContatoEntitie> atualizarCadastroContato(@PathVariable Long id, @Valid @RequestBody ContatoEntitie contatoAtualizado) {
		ContatoEntitie contact = contactService.updateContact(id, contatoAtualizado);
		log.info("Atualizando um unico contato pelo id: " + id);
		return ResponseEntity.ok(contact);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ContatoEntitie> deletarCadastroContato(@PathVariable Long id) {
		contactService.deleteContact(id);
		log.info("Deletando contato do id: " +  id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/strings")
	public String getString() {
		return "Testando chamada do endpoint";
	}

}
