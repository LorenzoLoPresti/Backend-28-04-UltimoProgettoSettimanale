package com.GestioneIncendi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.GestioneIncendi.model.Allarme;
import com.GestioneIncendi.model.Sonda;

@Repository
public interface SondaRepository extends CrudRepository<Sonda, Long> {

}
