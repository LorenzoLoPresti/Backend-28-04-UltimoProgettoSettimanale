package com.GestioneIncendi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestioneIncendi.model.GestoreSonde;
import com.GestioneIncendi.model.Sonda;
import com.GestioneIncendi.repository.CentroDiControlloRepository;
import com.GestioneIncendi.repository.GestoreSondeRepository;
import com.GestioneIncendi.repository.SondaRepository;
import com.github.javafaker.Faker;

@Service
public class CommandExecutorProxy implements CommandExecutor {

	@Autowired SondaRepository sondaRepo;
	@Autowired GestoreSondeRepository gestoreRepo;
	@Autowired CentroDiControlloRepository controlloRepo;
	
	public void controlloFumo(Sonda sonda) {
		// TODO Auto-generated method stub
	//	Sonda sonda = service.trovaSondaPerId(idSonda);
		Faker fake = new Faker();
		Integer lvFumo = fake.number().numberBetween(0, 10);
		sonda.setLivelloFumo(lvFumo);
		sondaRepo.save(sonda);
		GestoreSonde gestore = gestoreRepo.findById(sonda.getGestoreSondeAssociato().getId()).get();
		CentroDiControllo controllo = controlloRepo.findById(gestore.getCentroDiControllo().getId()).get();
		if(lvFumo > 5) {
//			gestore.setMessaggioAllarme("http://host/alarm?=idsonda=" + sonda.getId()
//					+ "&lat=" + sonda.getLatitudine() + "&lon=" + sonda.getLongitudine()
//					+ "&smokelevel=" + sonda.getLivelloFumo());
			gestore.setMessaggioAllarme(sonda.notifica());
			System.out.print("Sonda " + sonda.getId() + " : ");			
			System.out.println(gestore.notifica());
			System.out.println(controllo.notifica());
			System.out.println(controllo.AllertaPersonale(sonda.notifica() + "\n"));
			gestoreRepo.save(gestore);
			
		}else if(lvFumo <= 5 && gestore.getMessaggioAllarme() != null) {
			gestore.setAllarme(false);
			gestore.setMessaggioAllarme(null);
			gestoreRepo.save(gestore);
			System.out.println("Sonda " + sonda.getId() + " :Pericolo scampato \n");
		}
		else {
			System.out.println("Sonda " + sonda.getId() + " :Nessun pericolo \n");
		}
	} 
	
}
