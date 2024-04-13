package com.contact.finder.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.contact.finder.DTO.ContatoDTO;
import com.contact.finder.entities.ContatoEntity;
import com.contact.finder.exception.CustomExceptionNoContent;
import com.contact.finder.exception.CustomExceptionResponse;
import com.contact.finder.repository.ContatoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ContatoService {

	private final ContatoRepository contatoRepository;

	private final ModelMapper modelMapper;

	public List<ContatoDTO> buscarTodosContatos() {
		log.info("Service - Buscando todos os contatos");
		var contatoLista = contatoRepository.findAll();
		if (contatoLista.isEmpty()) {
			throw new CustomExceptionNoContent("Nenhum contato encontrado");
		}
		var listaContato = contatoLista.stream().map(entidade -> modelMapper.map(entidade, ContatoDTO.class))
				.collect(Collectors.toList());
		log.info("Service - Lista de contatos recuperada com sucesso");
		return listaContato;
	}

	public Optional<ContatoDTO> buscarContatoPeloId(Long id) {
		log.info("Service - Buscando contato pelo id: " + id);
		var contatoUnico = contatoRepository.findById(id);
		if (contatoUnico.isEmpty()) {
			throw new CustomExceptionResponse("Erro ao buscar o contato pelo ID: " + id);
		}
		var contatoDTO = contatoUnico.map(entity -> modelMapper.map(entity, ContatoDTO.class));
		log.info("Service - Contato do id " + id + " recuperada com sucesso");
		return contatoDTO;
	}

	public ContatoDTO criarContato(ContatoDTO contatoDTO) {
		log.info("Service - Recebendo dados do contato para cadastrar");
		var contatoEntity = modelMapper.map(contatoDTO, ContatoEntity.class);
		var ctConvertido = contatoRepository.save(contatoEntity);
		log.info("Contato " + contatoDTO.getNome() + " cadastrado com sucesso");
		return modelMapper.map(ctConvertido, ContatoDTO.class);
	}

	public ContatoDTO atualizarContato(Long id, ContatoDTO atualizarContatoDTO) {
		var buscandoContato = contatoRepository.existsById(id);
		if (!buscandoContato) {
			log.info("Service - Id: " + id + "inexistente");
			throw new CustomExceptionResponse("Erro ao buscar o contato pelo ID: " + id);
		}
		log.info("Service - Validando se o id: " + id + " existe");
		atualizarContatoDTO.setId(id);
		var contatoEntity = modelMapper.map(atualizarContatoDTO, ContatoEntity.class);
		var ctConvertido = contatoRepository.save(contatoEntity);
		return modelMapper.map(ctConvertido, ContatoDTO.class);
	}

	public void deletarContao(Long id) {
		log.info("Service - passando id de deleção");
		contatoRepository.deleteById(id);
	}
	
}
