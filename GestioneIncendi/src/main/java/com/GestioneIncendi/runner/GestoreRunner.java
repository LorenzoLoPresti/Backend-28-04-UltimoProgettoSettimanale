package com.GestioneIncendi.runner;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.GestioneIncendi.configuration.SondeFactory;
import com.GestioneIncendi.model.Allarme;
import com.GestioneIncendi.model.CentroDiControllo;
import com.GestioneIncendi.model.GestoreSonde;
import com.GestioneIncendi.model.Sonda;
import com.GestioneIncendi.service.AllarmeService;



@Component
public class GestoreRunner implements ApplicationRunner{

	@Autowired ObjectProvider<SondeFactory> allarmeProvider;
	@Autowired AllarmeService allarmeService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		
		// CREO DATABASE
		// creaDb();
		
		// FUNZIONE PERICOLO INCENDIO
		// All'update della sonda nel db se il livello fumo supera 5 si avvia
		// la catena di osservazione
		
//		Sonda sondaLetta = allarmeService.trovaSondaPerId(1l);
//		sondaLetta.setLivelloFumo(3);
//		allarmeService.updateSondaDb(sondaLetta);
//		scansiona();
		
		
		
		
	}
	
//	public void Scansiona(GestoreSonde gestore) {
//		if(gestore.getId() == null) {
//			throw new EntityNotFoundException();
//		}
//		GestoreSonde gestoreLetto = allarmeService.trovaGestorePerId(gestore.getId());
//		gestoreLetto.getListaSondeAssociate().forEach(e -> proxy.controlloFumo(e));
//	}
	
	public void scansiona() {
		List<GestoreSonde> listaGestori = allarmeService.listaGestori();
		if(listaGestori.size() == 0 || listaGestori == null) {
			System.out.println("nessun gestore attivo");
			return;
		}		
		System.out.println("\nResponso sonde:");
		for (GestoreSonde gestoreSonde : listaGestori) {			
			gestoreSonde.getListaSondeAssociate().forEach(e -> allarmeService.controlloFumo(e));
		}

	}
	
	public void creaDb() {
		//CREO DISPOSITIVI
		Allarme sonda1 =  allarmeProvider.getObject().creaDispositivo("sonda");
		Allarme sonda2 =  allarmeProvider.getObject().creaDispositivo("sonda");
		Allarme sonda3 =  allarmeProvider.getObject().creaDispositivo("sonda");
		Allarme gestore1 = allarmeProvider.getObject().creaDispositivo("gestore");
		Allarme centroControllo = allarmeProvider.getObject().creaDispositivo("controllo");
		
		// AGGIUNGO DISPOSITIVI AL DB
		allarmeService.creaDispositivo(sonda1);
		allarmeService.creaDispositivo(sonda2);
		allarmeService.creaDispositivo(sonda3);
		allarmeService.creaDispositivo(gestore1);
		allarmeService.creaDispositivo(centroControllo);
		
		// LEGGO DISPOSITIVI
		Sonda sondaLetta = allarmeService.trovaSondaPerId(1l);
		Sonda sondaLetta2 = allarmeService.trovaSondaPerId(2l);
		Sonda sondaLetta3 = allarmeService.trovaSondaPerId(3l);
		GestoreSonde gestoreLetto = allarmeService.trovaGestorePerId(1l);
		CentroDiControllo centroControlloLetto = allarmeService.trovaCentroControlloPerId(1l);
		
//		System.out.println(sondaLetta2);
//		System.out.println(sondaLetta3);
		
		// ASSOCIO GESTORE A SONDA
		sondaLetta.setGestoreSondeAssociato(gestoreLetto);
		sondaLetta2.setGestoreSondeAssociato(gestoreLetto);
		sondaLetta3.setGestoreSondeAssociato(gestoreLetto);
		
		// ASSOCIO SONDE A GESTORE
		gestoreLetto.getListaSondeAssociate().add(sondaLetta);
		gestoreLetto.getListaSondeAssociate().add(sondaLetta2);
		gestoreLetto.getListaSondeAssociate().add(sondaLetta3);
		
		// ASSOCIO CENTRO DI CONTROLLO A GESTORE
		gestoreLetto.setCentroDiControllo(centroControlloLetto);
		
		// ASSOCIO GESTORI A CENTRO DI CONTROLLO
		centroControlloLetto.getListaGestoriAssociati().add(gestoreLetto);
		
		// UPDATE SONDE E GESTORE
		allarmeService.updateSondaDb(sondaLetta);
		allarmeService.updateSondaDb(sondaLetta2);
		allarmeService.updateSondaDb(sondaLetta3);
		allarmeService.updateGestoreDb(gestoreLetto);
		allarmeService.updateCentroControllo(centroControlloLetto);
	}

}
