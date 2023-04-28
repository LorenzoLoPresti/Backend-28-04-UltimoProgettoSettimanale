package com.GestioneIncendi.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestioneIncendi.model.Allarme;
import com.GestioneIncendi.model.GestoreSonde;
import com.GestioneIncendi.model.Sonda;
import com.GestioneIncendi.repository.GestoreSondeRepository;
import com.GestioneIncendi.repository.SondaRepository;
import com.github.javafaker.Faker;

@Service
public class AllarmeService {

	@Autowired
	SondaRepository repoSonda;
	@Autowired
	GestoreSondeRepository repoGestore;

	public void creaDispositivo(Allarme disp) {
		Faker fake = Faker.instance(new Locale("it-IT"));
		if (disp instanceof Sonda) {
			Sonda sonda = (Sonda) disp;
			sonda.setLatitudine(fake.address().latitude());
			sonda.setLongitudine(fake.address().longitude());
			repoSonda.save(sonda);
			System.out.println("Sonda salvata nel db");
		} else if (disp instanceof GestoreSonde) {
			GestoreSonde gestore = (GestoreSonde) disp;
			gestore.setCitta(fake.address().city());
			gestore.setAllarme(false);
			repoGestore.save(gestore);
			System.out.println("Gestore salvato nel db");
		}
	}
	
	public Sonda trovaSondaPerId(Long id) {
		return repoSonda.findById(id).get();
	}
	
	public GestoreSonde trovaGestorePerId(Long id) {
		return repoGestore.findById(id).get();
	}
	
	public void updateSondaDb(Sonda sonda) {
		repoSonda.save(sonda);
		System.out.println("sonda aggiornata");
	}

	public void updateGestoreDb(GestoreSonde gestore) {
		repoGestore.save(gestore);
		System.out.println("gestore aggiornato");
	}
	
	
}
