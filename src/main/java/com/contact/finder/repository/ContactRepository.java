package com.contact.finder.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.contact.finder.entities.ContatoEntitie;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<ContatoEntitie, Long>{

	Optional<ContatoEntitie> findById(Long id);
	List<ContatoEntitie> findAll();

}
