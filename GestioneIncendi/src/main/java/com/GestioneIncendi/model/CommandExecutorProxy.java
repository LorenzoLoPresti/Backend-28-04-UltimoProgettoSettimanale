//package com.GestioneIncendi.model;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.GestioneIncendi.service.AllarmeService;
//
//
//public class CommandExecutorProxy implements CommandExecutor{
//
//	@Autowired AllarmeService service;
//	
//	@Override
//	public void controlloFumo(Integer lvFumo, Sonda sonda) {
//		// TODO Auto-generated method stub
//	//	Sonda sonda = service.trovaSondaPerId(idSonda);
//		sonda.setLivelloFumo(lvFumo);
//		service.updateSondaDb(sonda);
//		GestoreSonde gestore = service.trovaGestorePerId(sonda.getGestoreSondeAssociato().getId());
//		if(lvFumo > 5) {
//			gestore.setAllarme(true);
////			gestore.setMessaggioAllarme("http://host/alarm?=idsonda=" + sonda.getId()
////					+ "&lat=" + sonda.getLatitudine() + "&lon=" + sonda.getLongitudine()
////					+ "&smokelevel=" + sonda.getLivelloFumo());
//			gestore.setMessaggioAllarme(sonda.notifica());
//			System.out.println(sonda.notifica());
//			service.updateGestoreDb(gestore);
//			System.out.println("Pericolo incendio, segnalazione inviata al centro di controllo");
//		}else if(lvFumo <= 5 && gestore.getMessaggioAllarme() != null) {
//			gestore.setAllarme(false);
//			gestore.setMessaggioAllarme(null);
//			service.updateGestoreDb(gestore);
//		}
//		else {
//			System.out.println("Nessun pericolo");
//		}
//	} 
//
//}
