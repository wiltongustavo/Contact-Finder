package com.contact.finder.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.contact.finder.entities.ContatoEntity;
import com.contact.finder.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	
	
	public List<ContatoEntity> getAllContact(){
		log.info("Service buscar todos");
		return contactRepository.findAll();
	}
	
	public Optional<ContatoEntity> getContactById(Long id){
		log.info("Service buscando pelo id: " + id);
		return contactRepository.findById(id);
	}
	
	public ContatoEntity createContact(ContatoEntity contact) {
		log.info("Service passando dados do contato para salvar");
		return contactRepository.save(contact);
	}
	
	public ContatoEntity updateContact(Long id, ContatoEntity updateContact) {
		if(contactRepository.existsById(id)) {
			log.info("Service - Validando se o id: " + id + " existe");
			updateContact.setId(id);
			return contactRepository.save(updateContact);
		}else {
			log.info("Service - Id: " + id + "inexistente");
			throw new IllegalArgumentException("Contact with id " + id + " does not exist");
		}
	}
	
	public void deleteContact(Long id) {
		log.info("Service - passando id de deleção");
		contactRepository.deleteById(id);
	}
}
