package com.contact.finder.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.contact.finder.entities.ContatoEntity;


@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long>{

}
