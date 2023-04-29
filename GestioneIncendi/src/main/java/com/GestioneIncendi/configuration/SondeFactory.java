package com.GestioneIncendi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.GestioneIncendi.model.Allarme;
import com.GestioneIncendi.model.CentroDiControllo;
import com.GestioneIncendi.model.GestoreSonde;
import com.GestioneIncendi.model.Sonda;

@Configuration
public class SondeFactory {

	@Bean
	@Scope("prototype")
	public Allarme creaDispositivo(String tipo) {
		if (tipo == null) {
			return null;
		}
		else if(tipo.equalsIgnoreCase("sonda")) {
			return Sonda.builder().livelloFumo(0).build();
		} else if(tipo.equalsIgnoreCase("gestore")){
			return GestoreSonde.builder().allarme(false).build();
		}
		else if(tipo.equalsIgnoreCase("controllo")) {
			return new CentroDiControllo();
		}
		return null;
	}
	
}
