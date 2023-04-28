package com.GestioneIncendi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.GestioneIncendi.model.GestoreSonde;

@Repository
public interface GestoreSondeRepository extends CrudRepository<GestoreSonde, Long> {

	
	
}
