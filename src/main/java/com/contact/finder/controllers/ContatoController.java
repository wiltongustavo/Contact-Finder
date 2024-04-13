package com.contact.finder.controllers;

import java.util.List;

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

import com.contact.finder.DTO.ContatoDTO;
import com.contact.finder.entities.ContatoEntity;
import com.contact.finder.service.ContatoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/contatos")
@Tag(name = "Contatos", description = "API para gerenciar contatos")
public class ContatoController {

	private final ContatoService contatoService;

	@GetMapping
	@Operation(summary = "Obtém todos os contatos", description = "Retorna uma lista de contatos")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Contatos encontrados"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	public List<ContatoDTO> listarTodos() {
		log.info("Iniciando a listagem de todos os contatos ");
		return contatoService.buscarTodosContatos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtém um contato", description = "Retorna apenas um unico contato")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Contato encontrado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	public ResponseEntity<ContatoDTO> buscarContato(@PathVariable Long id) {
		var contato = contatoService.buscarContatoPeloId(id);
		log.info("Buscando um unico contato através do id: " + id);
		return contato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	@Operation(summary = "Cria um contato", description = "Retorna um contato criado")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Contato criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	public ResponseEntity<ContatoDTO> criarContato(@Valid @RequestBody ContatoDTO contactDTO) {
		var criarContatato = contatoService.criarContato(contactDTO);
		log.info("Criando um contato");
		return ResponseEntity.status(HttpStatus.CREATED).body(criarContatato);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza contato", description = "Atualiza o contato requisitado")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Contato atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	public ResponseEntity<ContatoDTO> atualizarCadastroContato(@PathVariable Long id,
			@Valid @RequestBody ContatoDTO contatoAtualizadoDTO) {
		var contato = contatoService.atualizarContato(id, contatoAtualizadoDTO);
		log.info("Atualizando um unico contato pelo id: " + id);
		return ResponseEntity.ok(contato);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta o contato", description = "Deleta um contato pelo id")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Contato deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	public ResponseEntity<ContatoEntity> deletarCadastroContato(@PathVariable Long id) {
		contatoService.deletarContao(id);
		log.info("Deletando contato do id: " + id);
		return ResponseEntity.noContent().build();
	}

}
