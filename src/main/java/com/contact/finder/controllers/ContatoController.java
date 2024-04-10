package com.contact.finder.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contact.finder.DTO.ContactDTO;
import com.contact.finder.entities.ContatoEntity;
import com.contact.finder.service.ContactService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/contatos")
@CrossOrigin(origins = "*")
public class ContatoController {

	@Autowired
	private ContactService contactService;

	@GetMapping
	public List<ContactDTO> listarTodos() {
		log.info("Iniciando a listagem de todos os contatos ");
		return contactService.getAllContact();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContactDTO> buscarContato(@PathVariable Long id) {
		Optional<ContactDTO> contact = contact = contactService.getContactById(id);
		log.info("Buscando um unico contato através do id: " + id);
		return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<ContactDTO> criarContato(@Valid @RequestBody ContactDTO contactDTO) {
		var createContact = contactService.createContact(contactDTO);
		log.info("Criando um contato");
		return ResponseEntity.status(HttpStatus.CREATED).body(createContact);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ContactDTO> atualizarCadastroContato(@PathVariable Long id,
			@Valid @RequestBody ContactDTO contatoAtualizadoDTO) {
		var contact = contactService.updateContact(id, contatoAtualizadoDTO);
		log.info("Atualizando um unico contato pelo id: " + id);
		return ResponseEntity.ok(contact);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ContatoEntity> deletarCadastroContato(@PathVariable Long id) {
		contactService.deleteContact(id);
		log.info("Deletando contato do id: " + id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/strings")
	public String getString() {
		return "Testando chamada do endpoint";
	}

}
