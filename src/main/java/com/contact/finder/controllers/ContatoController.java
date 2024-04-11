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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/contatos")
@CrossOrigin(origins = "*")
@Tag(name = "Contatos", description = "API para gerenciar contatos")
public class ContatoController {

	@Autowired
	private ContactService contactService;

	@GetMapping
	@Operation(summary = "Obtém todos os contatos", description = "Retorna uma lista de contatos")
	@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Contatos encontrados"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public List<ContactDTO> listarTodos() {
		log.info("Iniciando a listagem de todos os contatos ");
		return contactService.getAllContact();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtém um contato", description = "Retorna apenas um unico contato")
	@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Contato encontrado"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<ContactDTO> buscarContato(@PathVariable Long id) {
		Optional<ContactDTO> contact = contact = contactService.getContactById(id);
		log.info("Buscando um unico contato através do id: " + id);
		return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	@Operation(summary = "Cria um contato", description = "Retorna um contato criado")
	@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Contato criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<ContactDTO> criarContato(@Valid @RequestBody ContactDTO contactDTO) {
		var createContact = contactService.createContact(contactDTO);
		log.info("Criando um contato");
		return ResponseEntity.status(HttpStatus.CREATED).body(createContact);

	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza contato", description = "Atualiza o contato requisitado")
	@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Contato atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<ContactDTO> atualizarCadastroContato(@PathVariable Long id,
			@Valid @RequestBody ContactDTO contatoAtualizadoDTO) {
		var contact = contactService.updateContact(id, contatoAtualizadoDTO);
		log.info("Atualizando um unico contato pelo id: " + id);
		return ResponseEntity.ok(contact);

	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta o contato", description = "Deleta um contato pelo id")
	@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Contato deletado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
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
