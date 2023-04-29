package com.GestioneIncendi.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestioneIncendi.model.Allarme;
import com.GestioneIncendi.model.CentroDiControllo;
import com.GestioneIncendi.model.GestoreSonde;
import com.GestioneIncendi.model.Sonda;
import com.GestioneIncendi.repository.CentroDiControlloRepository;
import com.GestioneIncendi.repository.GestoreSondeRepository;
import com.GestioneIncendi.repository.SondaRepository;
import com.github.javafaker.Faker;

@Service
public class AllarmeService {

	@Autowired
	SondaRepository repoSonda;
	@Autowired
	GestoreSondeRepository repoGestore;
	@Autowired
	CentroDiControlloRepository repoControllo;

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
			repoGestore.save(gestore);
			System.out.println("Gestore salvato nel db");
		} else if(disp instanceof CentroDiControllo) {
			CentroDiControllo centro = (CentroDiControllo) disp;
			repoControllo.save(centro);
		} 
	}
	
	public Sonda trovaSondaPerId(Long id) {
		return repoSonda.findById(id).get();
	}
	
	public GestoreSonde trovaGestorePerId(Long id) {
		return repoGestore.findById(id).get();
	}
	
	public CentroDiControllo trovaCentroControlloPerId(Long id) {
		return repoControllo.findById(id).get();
	}
	
	public void updateSondaDb(Sonda sonda) {
		repoSonda.save(sonda);
		System.out.println("sonda aggiornata");
	}

	public void updateGestoreDb(GestoreSonde gestore) {
		repoGestore.save(gestore);
		System.out.println("gestore aggiornato");
	}
	
	public void updateCentroControllo(CentroDiControllo centro) {
		repoControllo.save(centro);
	}
	
	public List<GestoreSonde> listaGestori(){
		return (List<GestoreSonde>) repoGestore.findAll();
	}
	
	public List<Sonda> listaSonde(){
		return (List<Sonda>) repoSonda.findAll();
	}
	
	public List<CentroDiControllo> listaCentriControllo(){
		return (List<CentroDiControllo>) repoControllo.findAll();
	}
	
}
