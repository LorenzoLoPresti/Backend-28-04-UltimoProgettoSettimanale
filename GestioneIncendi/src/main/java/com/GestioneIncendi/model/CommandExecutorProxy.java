package com.GestioneIncendi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestioneIncendi.model.GestoreSonde;
import com.GestioneIncendi.model.Sonda;
import com.GestioneIncendi.repository.GestoreSondeRepository;
import com.GestioneIncendi.repository.SondaRepository;

@Service
public class CommandExecutorProxy implements CommandExecutor {

	@Autowired SondaRepository sondaRepo;
	@Autowired GestoreSondeRepository gestoreRepo;
	
	public void controlloFumo(Integer lvFumo, Sonda sonda) {
		// TODO Auto-generated method stub
	//	Sonda sonda = service.trovaSondaPerId(idSonda);
		sonda.setLivelloFumo(lvFumo);
		sondaRepo.save(sonda);
		GestoreSonde gestore = gestoreRepo.findById(sonda.getGestoreSondeAssociato().getId()).get();
		if(lvFumo > 5) {
			gestore.setAllarme(true);
//			gestore.setMessaggioAllarme("http://host/alarm?=idsonda=" + sonda.getId()
//					+ "&lat=" + sonda.getLatitudine() + "&lon=" + sonda.getLongitudine()
//					+ "&smokelevel=" + sonda.getLivelloFumo());
			gestore.setMessaggioAllarme(sonda.notifica());
			System.out.println(sonda.notifica());
			gestoreRepo.save(gestore);
			System.out.println("Pericolo incendio, segnalazione inviata al centro di controllo");
		}else if(lvFumo <= 5 && gestore.getMessaggioAllarme() != null) {
			gestore.setAllarme(false);
			gestore.setMessaggioAllarme(null);
			gestoreRepo.save(gestore);
			System.out.println("Pericolo scampato");
		}
		else {
			System.out.println("Nessun pericolo");
		}
	} 
	
}
