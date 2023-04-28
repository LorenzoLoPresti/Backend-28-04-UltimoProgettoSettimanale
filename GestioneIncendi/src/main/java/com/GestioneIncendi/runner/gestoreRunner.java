package com.GestioneIncendi.runner;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.GestioneIncendi.configuration.SondeFactory;
import com.GestioneIncendi.model.Allarme;
import com.GestioneIncendi.model.CommandExecutorProxy;
import com.GestioneIncendi.model.GestoreSonde;
import com.GestioneIncendi.model.Sonda;
import com.GestioneIncendi.service.AllarmeService;


@Component
public class gestoreRunner implements ApplicationRunner{

	@Autowired ObjectProvider<SondeFactory> allarmeProvider;
	@Autowired AllarmeService allarmeService;
	@Autowired CommandExecutorProxy proxy;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Run...");
		
		
		//CREO DISPOSITIVI
		Allarme sonda1 =  allarmeProvider.getObject().creaDispositivo("sonda");
		Allarme sonda2 =  allarmeProvider.getObject().creaDispositivo("sonda");
		Allarme sonda3 =  allarmeProvider.getObject().creaDispositivo("sonda");
		Allarme gestore1 = allarmeProvider.getObject().creaDispositivo("gestore");
		
		// AGGIUNGO DISPOSITIVI AL DB
//		allarmeService.creaDispositivo(sonda1);
//		allarmeService.creaDispositivo(sonda2);
//		allarmeService.creaDispositivo(sonda3);
//		allarmeService.creaDispositivo(gestore1);
		
		// LEGGO DISPOSITIVI
		Sonda sondaLetta = allarmeService.trovaSondaPerId(1l);
		Sonda sondaLetta2 = allarmeService.trovaSondaPerId(2l);
		Sonda sondaLetta3 = allarmeService.trovaSondaPerId(3l);
		GestoreSonde gestoreLetto = allarmeService.trovaGestorePerId(1l);
		
//		System.out.println(sondaLetta2);
//		System.out.println(sondaLetta3);
		
		// ASSOCIO GESTORE A SONDA
//		sondaLetta.setGestoreSondeAssociato(gestoreLetto);
//		sondaLetta2.setGestoreSondeAssociato(gestoreLetto);
//		sondaLetta3.setGestoreSondeAssociato(gestoreLetto);
		
		// ASSOCIO SONDE A GESTORE
//		gestoreLetto.getListaSondeAssociate().add(sondaLetta);
//		gestoreLetto.getListaSondeAssociate().add(sondaLetta2);
//		gestoreLetto.getListaSondeAssociate().add(sondaLetta3);
		
		// UPDATE SONDE E GESTORE
//		allarmeService.updateSondaDb(sondaLetta);
//		allarmeService.updateSondaDb(sondaLetta2);
//		allarmeService.updateSondaDb(sondaLetta3);
//		allarmeService.updateGestoreDb(gestoreLetto);
		//allarmeService.creaDispositivo(gestore1);
		
//		System.out.println(sondaLetta);
//		System.out.println(gestoreLetto);
		
		// FUNZIONE PERICOLO INCENDIO
		proxy.controlloFumo(4, sondaLetta);
		System.out.println(sondaLetta);
		
		
		
		
	}

}
