package com.contact.finder.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.contact.finder.DTO.ContactDTO;
import com.contact.finder.entities.ContatoEntity;
import com.contact.finder.repository.ContactRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ContactService {

	private final ContactRepository contactRepository;

	private final ModelMapper modelMapper;

	public List<ContactDTO> getAllContact() {
		log.info("Service buscar todos");
		final var contatoLista = contactRepository.findAll();

		return contatoLista.stream().map(entidade -> modelMapper.map(entidade, ContactDTO.class))
				.collect(Collectors.toList());

	}

	public Optional<ContactDTO> getContactById(Long id) {
		log.info("Service buscando pelo id: " + id);
		Optional<ContatoEntity> contatoUnico = contactRepository.findById(id);
	    Optional<ContactDTO> contatoDTO = contatoUnico.map(entity -> modelMapper.map(entity, ContactDTO.class));
		return contatoDTO;
	}

	public ContactDTO createContact(ContactDTO contatoDTO) {
		log.info("Service passando dados do contato para salvar");
		final var contatoEntity = modelMapper.map(contatoDTO, ContatoEntity.class);
		final var ctConvertido = contactRepository.save(contatoEntity);
		log.info("Contato" + contatoDTO.getNome() + "cadastrado com sucesso");
		return modelMapper.map(ctConvertido, ContactDTO.class);
	}

	public ContactDTO updateContact(Long id, ContactDTO atualizarContatoDTO) {
		if (contactRepository.existsById(id)) {
			log.info("Service - Validando se o id: " + id + " existe");
			atualizarContatoDTO.setId(id);
			final var contatoEntity = modelMapper.map(atualizarContatoDTO, ContatoEntity.class);
			final var ctConvertido = contactRepository.save(contatoEntity);
			return modelMapper.map(ctConvertido, ContactDTO.class);
		} else {
			log.info("Service - Id: " + id + "inexistente");
			throw new IllegalArgumentException("Contact with id " + id + " does not exist");
		}
	}

	public void deleteContact(Long id) {
		log.info("Service - passando id de deleção");
		contactRepository.deleteById(id);
	}
}
